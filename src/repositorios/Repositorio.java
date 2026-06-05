package src.repositorios;

import java.util.Vector;
import src.excecoes.ObraJaCadastradaException;
import src.excecoes.ObraNaoEncontradaException;
import src.interfaces.IRepositorioObra;
import src.tipos_obra.Obra;

public class Repositorio implements IRepositorioObra{

    Vector<Obra> obras = new Vector<>();

    @Override
    public void cadastrar(Obra obra) throws ObraJaCadastradaException{
        Obra buscarObra = this.buscar(obra.getTitulo());
        if (buscarObra == null) {
            this.obras.add(obra);
            return;
        }
        throw new ObraJaCadastradaException("Obra " + obra.getTitulo() + " já foi cadastrada!!\n");
    }

    @Override
    public Obra buscar(String titulo){
        for (Obra obra : obras){
            if(obra.getTitulo().equals(titulo)){
                return obra;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Obra obra) throws ObraNaoEncontradaException{
        Obra buscarObra = this.buscar(obra.getTitulo());
        if (buscarObra == null){
            throw new ObraNaoEncontradaException("Obra " + obra.getTitulo() + " não encontrada!!");
        }
        int indice = this.obras.indexOf(buscarObra);
        this.obras.set(indice, obra);
    }

    @Override
    public void remover(String titulo){
        Obra buscarObra = this.buscar(titulo);
        this.obras.remove(buscarObra);
    }

    @Override
    public Vector<Obra> listar(){
        return this.obras;
    }

    @Override
    public Vector<Obra> listarPorAutor(String autor){
        Vector<Obra> autorObras = new Vector<>();
        for( Obra obra : this.obras){
            if(obra.getAutor().equals(autor)){
                autorObras.add(obra);
            }
        }
        return autorObras;
    }

}

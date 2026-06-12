package src.repositorios;

import java.util.Vector;

import src.excecoes.ObraJaCadastradaException;
import src.excecoes.ObraNaoEncontradaException;
import src.interfaces.IRepositorioObra;
import src.tiposObra.Obra;

public class Repositorio implements IRepositorioObra{

    Vector<Obra> obras = new Vector<>();

    @Override
    public void cadastrar(Obra obra) throws ObraJaCadastradaException{
        Vector<Obra> buscarObra = this.buscar(obra.getTitulo());
        if (buscarObra == null) {
            this.obras.add(obra);
            return;
        }
        for (Obra o: buscarObra){
            if (o.getAutor().equals(obra.getAutor())) {
                throw new ObraJaCadastradaException("A obra " + obra.getTitulo() + " do " + obra.getAutor() + "já foi cadastrada!!\n");
            }
        }
        this.obras.add(obra);
    }

    @Override
    public Vector<Obra> buscar(String titulo){
        Vector<Obra> obras_encontradas = new Vector<>();
        for (Obra obra : obras){
            if(obra.getTitulo().equals(titulo)){
                obras_encontradas.add(obra);
            }
        }
        if (obras_encontradas.isEmpty()){
            return null;
        }
        return obras_encontradas;
    }

    @Override
    public void atualizar(Obra obra) throws ObraNaoEncontradaException{
        Vector<Obra> buscarObra = this.buscar(obra.getTitulo());
        if (buscarObra == null){
            throw new ObraNaoEncontradaException("A obra " + obra.getTitulo() + " do " + obra.getAutor() + " não foi encontrada!!");
        }
        int indice = -1;
        for(Obra o : buscarObra){
            if(o.getAutor().equals(obra.getAutor()) && o.getTitulo().equals(obra.getTitulo()))
                indice = this.obras.indexOf(o);
        }
        if(indice == -1){
            throw new ObraNaoEncontradaException("A obra " + obra.getTitulo() + " do " + obra.getAutor() + " não foi encontrada!!");
        }
        this.obras.set(indice, obra);
    }

    @Override
    public void remover(String titulo, String autor) throws ObraNaoEncontradaException{
        Vector<Obra> buscarObra = this.buscar(titulo);
        if (buscarObra == null){
            throw new ObraNaoEncontradaException("A obra " + titulo + " do " + autor + " não foi encontrada!!");
        }
        for(Obra obra : buscarObra){
            if(obra.getAutor().equals(autor)){
                this.obras.remove(obra);
                return;
            }
        }
        throw new ObraNaoEncontradaException("A obra " + titulo + " do " + autor + " não foi encontrada!!");
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

    @Override
    public Obra buscarObra(String titulo, String autor) throws ObraNaoEncontradaException{
        Vector<Obra> obrasEncontradas = this.buscar(titulo);
        for(Obra o: obrasEncontradas){
            if(o.getAutor().equals(autor)){
                return o;
            }
        }
        throw new ObraNaoEncontradaException("A obra " + titulo + " do " + autor + " não foi encontrada!!");
    }
}

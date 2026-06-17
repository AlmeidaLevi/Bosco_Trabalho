package src.repositorios;

import java.util.Vector;

import src.entidades.tiposObra.Obra;
import src.excecoes.ObraJaCadastradaException;
import src.excecoes.ObraNaoEncontradaException;
import src.interfaces.IRepositorioObra;

public class Repositorio implements IRepositorioObra{

    Vector<Obra> obras = new Vector<>();

    @Override
    public void cadastrar(Obra obra) throws ObraJaCadastradaException{
        Vector<Obra> buscarObra = this.buscar(obra.getTitulo());
        if (buscarObra == null) {
            this.obras.add(obra);
            return;
        }

        // Verificando se a obra existe
        for (Obra o: buscarObra){
            if (o.getAutor().equals(obra.getAutor())) {
                throw new ObraJaCadastradaException("A obra " + obra.getTitulo() + " do " + obra.getAutor() + " já foi cadastrada!!\n");
            }
        }

        this.obras.add(obra);
    }

    @Override
    public Vector<Obra> buscar(String titulo){
        Vector<Obra> obrasEncontradas = new Vector<>();
        // Pegando todas as obras com o mesmo titulo
        for (Obra obra : this.obras){
            if(obra.getTitulo().equals(titulo)){
                obrasEncontradas.add(obra);
            }
        }

        // Retornando null caso não haja obra com o titulo
        if (obrasEncontradas.isEmpty()){
            return null;
        }

        return obrasEncontradas;
    }

    @Override
    public void atualizar(Obra obra) throws ObraNaoEncontradaException{
        Obra buscarObra = this.buscarObra(obra.getTitulo(), obra.getAutor());
        int indice = this.obras.indexOf(buscarObra);
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
        if(obrasEncontradas == null){
            throw new ObraNaoEncontradaException("A obra " + titulo + " do " + autor + " não foi encontrada!!");
        }
        for(Obra o: obrasEncontradas){
            if(o.getAutor().equals(autor)){
                return o;
            }
        }
        throw new ObraNaoEncontradaException("A obra " + titulo + " do " + autor + " não foi encontrada!!");
    }
}

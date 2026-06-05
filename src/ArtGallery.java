package src;

import java.util.Vector;
import src.interfaces.IArtGallery;
import src.interfaces.IRepositorioObra;
import src.tipos_obra.Obra;


public class ArtGallery implements IArtGallery {

    private IRepositorioObra repositorio;

    public ArtGallery(IRepositorioObra repositorio) {

    }

    @Override
    public void publicarObra(Obra obra) {

    }

    @Override
    public void removerObra(String titulo) {

    }

    @Override
    public void avaliarObra(String titulo, Avaliacao avaliacao) {

    }

    @Override
    public Vector<Obra> listarObras() {
        return this.repositorio.listar();
    }

    @Override
    public Vector<Obra> buscarPorAutor(String autor) {
        return this.repositorio.listarPorAutor(autor);
    }

    @Override
    public Vector<Obra> topObras() {
        return this.repositorio.listar();
    }

    @Override
    public Vector<Obra> obrasExpostas(String nomeExposicao) {
        return this.repositorio.listar();
    }

}

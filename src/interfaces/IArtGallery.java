package src.interfaces;

import java.util.Vector;

import src.Avaliacao;
import src.tipos_obra.Obra;

public interface IArtGallery {

    public void publicarObra(Obra obra);

    public void removerObra(String titulo);

    public void avaliarObra(String titulo, Avaliacao avaliacao);

    public Vector<Obra> listarObras();

    public Vector<Obra> buscarPorAutor(String autor);

    public Vector<Obra> topObras();

    public Vector<Obra> obrasExpostas(String nomeExposicao);

}

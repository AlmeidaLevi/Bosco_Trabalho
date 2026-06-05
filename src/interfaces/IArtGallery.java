package src.interfaces;

import java.util.Vector;
import src.excecoes.ExposicaoNaoEncontradaException;
import src.excecoes.ObraJaCadastradaException;
import src.excecoes.ObraNaoEncontradaException;
import src.tipos_obra.Obra;
import src.utils.Avaliacao;

public interface IArtGallery {

    public void publicarObra(Obra obra) throws ObraJaCadastradaException;

    public void removerObra(String titulo);

    public void avaliarObra(String titulo, Avaliacao avaliacao) throws ObraNaoEncontradaException;

    public Vector<Obra> listarObras();

    public Vector<Obra> buscarPorAutor(String autor);

    public Vector<Obra> topObras();

    public Vector<Obra> obrasExpostas(String nomeExposicao) throws ExposicaoNaoEncontradaException;

}

package src.interfaces;

import java.util.Vector;

import src.entidades.tiposObra.Obra;
import src.excecoes.ExposicaoJaCadastradaException;
import src.excecoes.ExposicaoNaoEncontradaException;
import src.excecoes.ObraJaCadastradaException;
import src.excecoes.ObraNaoEncontradaException;
import src.entidades.Avaliacao;
import src.entidades.Exposicao;

public interface IArtGallery {

    public void publicarObra(Obra obra) throws ObraJaCadastradaException;

    public void removerObra(String titulo, String autor) throws ObraNaoEncontradaException;

    public void avaliarObra(Obra obra, Avaliacao avaliacao) throws ObraNaoEncontradaException;

    public Vector<Obra> listarObras();

    public Vector<Obra> buscarPorAutor(String autor);

    public Vector<Obra> topObras();

    public void adicionarExposicao(Exposicao exposicao) throws ExposicaoJaCadastradaException;

    public Vector<Obra> obrasExpostas(String nomeExposicao) throws ExposicaoNaoEncontradaException;

    public Obra buscarObra(String titulo, String autor);
}

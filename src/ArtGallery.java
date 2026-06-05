package src;

import java.util.Comparator;
import java.util.Vector;
import src.excecoes.ExposicaoNaoEncontradaException;
import src.excecoes.ObraJaCadastradaException;
import src.excecoes.ObraNaoEncontradaException;
import src.interfaces.IArtGallery;
import src.interfaces.IRepositorioObra;
import src.tipos_obra.Obra;
import src.utils.Avaliacao;
import src.utils.Exposicao;


public class ArtGallery implements IArtGallery {

    private IRepositorioObra repositorio;
    private Vector<Exposicao> exposicoes;

    public ArtGallery(IRepositorioObra repositorio) {
        this.repositorio = repositorio;
        this.exposicoes = new Vector<>();
    }

    @Override
    public void publicarObra(Obra obra) throws ObraJaCadastradaException {
        repositorio.cadastrar(obra);
    }

    @Override
    public void removerObra(String titulo) {
        repositorio.remover(titulo);
    }

    @Override
    public void avaliarObra(String titulo, Avaliacao avaliacao) throws ObraNaoEncontradaException{
        Obra buscarObra = repositorio.buscar(titulo);
        if(buscarObra == null || !buscarObra.isAtiva()){
            throw new ObraNaoEncontradaException("Obra " + titulo + " não encontrada!!");
        }
        buscarObra.adicionarAvaliacao(avaliacao);

    }

    @Override
    public Vector<Obra> listarObras() {
        Vector<Obra> obras = this.repositorio.listar();
        Vector<Obra> obras_ativas = new Vector<>();

        for(Obra obra : obras){
            if(obra.isAtiva()){
                obras_ativas.add(obra);
            }
        }

        return obras_ativas;
    }

    @Override
    public Vector<Obra> buscarPorAutor(String autor) {
        return this.repositorio.listarPorAutor(autor);
    }

    @Override
    public Vector<Obra> topObras() {
        Vector<Obra> obras = this.repositorio.listar();
        obras.sort(Comparator.comparing(Obra::mediaAvaliacoes));
        return obras;

    }

    @Override
    public Vector<Obra> obrasExpostas(String nomeExposicao) throws ExposicaoNaoEncontradaException{
        Exposicao exposicao = null;
        for (Exposicao ex : this.exposicoes){
            if(ex.getNome().equals(nomeExposicao)){
                exposicao = ex;
            }
        }
        if(exposicao == null){
            throw new ExposicaoNaoEncontradaException("Exposição " + nomeExposicao + " não encontrada");
        }
        return exposicao.listarObras();
    }

    public void adicionarExposicao(Exposicao exposicao){
        this.exposicoes.add(exposicao);
    }

}

package src;

import java.util.Comparator;
import java.util.Vector;
import src.excecoes.ExposicaoNaoEncontradaException;
import src.excecoes.ObraJaCadastradaException;
import src.excecoes.ObraNaoEncontradaException;
import src.interfaces.IArtGallery;
import src.interfaces.IRepositorioObra;
import src.tiposObra.Obra;
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
    public void removerObra(String titulo, String autor) throws ObraNaoEncontradaException {
        repositorio.remover(titulo, autor);
    }

    @Override
    public void avaliarObra(Obra obra, Avaliacao avaliacao) throws ObraNaoEncontradaException{
        Vector<Obra> buscarObra = repositorio.buscar(obra.getTitulo());

        if(buscarObra == null){
            throw new ObraNaoEncontradaException("Obra " + obra.getTitulo() + " do " + obra.getAutor() +" não foi encontrada!!");
        }

        for(Obra o: buscarObra){
            if(o.getAutor() == obra.getAutor()){
                o.adicionarAvaliacao(avaliacao);
                return;
            }
        }

        throw new ObraNaoEncontradaException("Obra " + obra.getTitulo() + " do " + obra.getAutor() +" não foi encontrada!!");

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

    @Override
    public Obra buscarObra(String titulo, String autor){
        try{
            Obra obra = repositorio.buscarObra(titulo, autor);
            return obra;
        } catch (ObraNaoEncontradaException e){
            return null;
        }
    }
}

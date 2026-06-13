package src.tiposObra;

import java.util.Vector;
import src.utils.Avaliacao;

public abstract class Obra {
    private String titulo;
    private String autor;
    private boolean ativa;
    private Vector<Avaliacao> avaliacoes;

    public Obra(String titulo, String autor){
        this.titulo = titulo;
        this.autor = autor;
        this.ativa = true;
        this.avaliacoes = new Vector<>();
    }


    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public boolean isAtiva() {
        return this.ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public Vector<Avaliacao> listarAvaliacoes() {
        return this.avaliacoes;
    }

    public double mediaAvaliacoes() {
        int media = 0;
        for (Avaliacao i : this.avaliacoes){
            media += i.getNota();
        }
        media /= this.avaliacoes.size();
        return media;
    }

    public abstract String exibirDetalhes();
}

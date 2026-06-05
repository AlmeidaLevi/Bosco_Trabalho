package src;
import src.excecoes.ObraJaCadastradaException;

public class Avaliacao {
    private String usuario;
    private int nota;
    private String comentario;

    public Avaliacao(String usuario, int nota, String comentario) throws ObraJaCadastradaException{
        if (nota > 10 || nota < 0){
            throw new ObraJaCadastradaException("A nota deve estar entre 0 e 10");
        }
        this.usuario = usuario;
        this.nota = nota;
        this.comentario = comentario;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public int getNota() {
        return this.nota;
    }

    public String getComentario() {
        return this.comentario;
    }
}

package src.utils;

public class Avaliacao {
    private String usuario;
    private int nota;
    private String comentario;

    public Avaliacao(String usuario, int nota, String comentario){
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

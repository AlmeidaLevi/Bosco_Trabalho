package src.excecoes;

public class NotaInvalidaException extends RuntimeException {
    public NotaInvalidaException(String mensagem){
        super(mensagem);
    }
}

package src.excecoes;

public class ObraNaoEncontradaException extends RuntimeException {
    public ObraNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}

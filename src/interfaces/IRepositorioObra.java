package src.interfaces;
import java.util.Vector;
import src.excecoes.ObraJaCadastradaException;
import src.excecoes.ObraNaoEncontradaException;
import src.tipos_obra.Obra;

public interface IRepositorioObra {

    public void cadastrar(Obra obra) throws ObraJaCadastradaException;

    public Vector<Obra> buscar(String titulo);

    public void atualizar(Obra obra) throws ObraNaoEncontradaException;

    public void remover(String titulo, String autor) throws ObraNaoEncontradaException;

    public Vector<Obra> listar();

    public Vector<Obra> listarPorAutor(String autor);

}

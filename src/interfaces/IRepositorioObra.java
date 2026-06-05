package src.interfaces;
import java.util.Vector;
import src.excecoes.ObraJaCadastradaException;
import src.excecoes.ObraNaoEncontradaException;
import src.tipos_obra.Obra;

public interface IRepositorioObra {

    public void cadastrar(Obra obra) throws ObraJaCadastradaException;

    public Obra buscar(String titulo);

    public void atualizar(Obra obra) throws ObraNaoEncontradaException;

    public void remover(String titulo);

    public Vector<Obra> listar();

    public Vector<Obra> listarPorAutor(String autor);

}

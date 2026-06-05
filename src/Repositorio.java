package src;

import java.util.Vector;
import src.excecoes.ObraJaCadastradaException;
import src.interfaces.IRepositorioObra;
import src.tipos_obra.Obra;

public class Repositorio implements IRepositorioObra{

    Vector<Obra> obras = new Vector<>();

    @Override
    public void cadastrar(Obra obra) throws ObraJaCadastradaException{

    }

}

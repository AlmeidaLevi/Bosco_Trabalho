package src.utils;
import java.util.Vector;
import src.tipos_obra.Obra;

public class Exposicao {

    private String nome;
    private Vector<Obra> obras;

    public Exposicao(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void adicionarObra(Obra obra) {
        this.obras.add(obra);
    }

    public Vector<Obra> listarObras() {
        return this.obras;
    }

}

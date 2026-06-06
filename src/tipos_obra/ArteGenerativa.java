package src.tipos_obra;

public class ArteGenerativa extends Obra{
    private String algoritmo;
    private long seed;

    public ArteGenerativa(String titulo, String autor, long seed, String algoritmo){
        super(titulo, autor);
        this.algoritmo = algoritmo;
        this.seed = seed;
    }

    public String getAlgoritmo(){
        return this.algoritmo;
    }

    public long getSoftware(){
        return this.seed;
    }

    @Override
    public String exibirDetalhes(){
        String titulo = "Titulo: " + this.getTitulo() + "\n";
        String autor = "Titulo: " + this.getAutor() + "\n";
        String algoritmo = "Algoritmo: " + this.getAlgoritmo() + "\n";
        String seed = "Seed: " + this.getSoftware() + "\n";

        return titulo + autor + "Tipo: Pintura Digital\n" + algoritmo + seed;
    }
}

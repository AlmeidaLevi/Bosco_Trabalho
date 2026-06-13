package src.tiposObra;

public class Modelagem3D extends Obra{
    private int numeroPoligonos;
    private String Engine;

    public Modelagem3D(String titulo, String autor, int numeroPoligonos, String engine){
        super(titulo, autor);
        this.numeroPoligonos = numeroPoligonos;
        this.Engine = engine;
    }

    public int getNumeroPoligonos(){
        return this.numeroPoligonos;
    }

    public String getSoftware(){
        return this.Engine;
    }

    @Override
    public String exibirDetalhes(){
        String titulo = "Titulo: " + this.getTitulo() + "\n";
        String autor = "Autor: " + this.getAutor() + "\n";
        String num_poligonos = "Poligonos: " + this.getNumeroPoligonos() + "\n";
        String engine = "Engine: " + this.getSoftware() + "\n";

        return titulo + autor + "Tipo: Pintura Digital\n" + num_poligonos + engine;
    }
}


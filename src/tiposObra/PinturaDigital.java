package src.tiposObra;

public class PinturaDigital extends Obra{
    private String resolucao;
    private String softwareUtilizado;

    public PinturaDigital(String titulo, String autor, String resolucao, String softwareUtilizado){
        super(titulo, autor);
        this.resolucao = resolucao;
        this.softwareUtilizado = softwareUtilizado;
    }

    public String getResolucao(){
        return this.resolucao;
    }

    public String getSoftware(){
        return this.softwareUtilizado;
    }

    @Override
    public String exibirDetalhes(){
        String titulo = "Titulo: " + this.getTitulo() + "\n";
        String autor = "Autor: " + this.getAutor() + "\n";
        String resolucao = "Resolucao: " + this.getResolucao() + "\n";
        String software = "Software: " + this.getSoftware() + "\n";
        String media = "Nota media: " + this.getMediaAvaliacoes() + "\n";

        return titulo + autor + "Tipo: Pintura Digital\n" + resolucao + software + media;
    }
}

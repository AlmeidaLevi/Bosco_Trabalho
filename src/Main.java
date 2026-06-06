package src;

import src.repositorios.Repositorio;
import src.windows.MainWindow;

public class Main {
    public static void main(String[] args) {
        Repositorio repositorio = new Repositorio();
        ArtGallery galeria = new ArtGallery(repositorio);
        MainWindow janela = new MainWindow(galeria);
        janela.setVisible(true);
    }
}

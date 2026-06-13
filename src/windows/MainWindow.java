package src.windows;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.interfaces.IArtGallery;
import src.windows.paineis.AvaliarObraPainel;
import src.windows.paineis.BuscarObrasPorAutorPainel;
import src.windows.paineis.ExibirTopObras;
import src.windows.paineis.ListarObrasPainel;
import src.windows.paineis.MenuPrincipalPainel;
import src.windows.paineis.PublicarObraPainel;
import src.windows.paineis.RemoverObraPainel;

public class MainWindow extends JFrame{

    public MainWindow (IArtGallery galeria){
        CardLayout layout = new CardLayout();
        JPanel conteiner = new JPanel(layout);

        conteiner.add(new MenuPrincipalPainel(conteiner, layout), "Menu Principal");
        conteiner.add(new PublicarObraPainel(conteiner, layout, galeria), "Publicar Obra");
        conteiner.add(new RemoverObraPainel(conteiner, layout, galeria), "Remover Obra");
        conteiner.add(new AvaliarObraPainel(conteiner, layout, galeria), "Avaliar Obra");
        conteiner.add(new ListarObrasPainel(conteiner, layout, galeria), "Listar Obras");
        conteiner.add(new BuscarObrasPorAutorPainel(conteiner, layout, galeria), "Buscar obras por autor");
        conteiner.add(new ExibirTopObras(conteiner, layout, galeria), "Exibir TOP obras");

        add(conteiner);

        setTitle("Art Gallery");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

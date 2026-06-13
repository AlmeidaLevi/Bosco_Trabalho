package src.windows;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import src.interfaces.IArtGallery;
import src.windows.paineis.PublicarObraPainel;
import src.windows.paineis.RemoverObraPainel;
import src.windows.paineis.MenuPrincipalPainel;
import src.windows.paineis.AvaliarObraPainel;

public class MainWindow extends JFrame{
    public MainWindow (IArtGallery galeria){
        CardLayout layout = new CardLayout();
        JPanel conteiner = new JPanel(layout);

        conteiner.add(new MenuPrincipalPainel(conteiner, layout), "Menu Principal");
        conteiner.add(new PublicarObraPainel(conteiner, layout, galeria), "Publicar Obra");
        conteiner.add(new RemoverObraPainel(conteiner, layout, galeria), "Remover Obra");
        conteiner.add(new AvaliarObraPainel(conteiner, layout, galeria), "Avaliar Obra");

        add(conteiner);

        setTitle("Art Gallery");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

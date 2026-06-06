package src.windows;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import src.interfaces.IArtGallery;
import src.windows.paineis.AdicionarObraPainel;
import src.windows.paineis.MenuPrincipalPainel;

public class MainWindow extends JFrame{
    public MainWindow (IArtGallery galeria){
        CardLayout layout = new CardLayout();
        JPanel container = new JPanel(layout);

        container.add(new MenuPrincipalPainel(container, layout), "Menu Principal");
        container.add(new AdicionarObraPainel(container, layout, galeria), "Adicionar Obra");

        add(container);

        setTitle("Art Gallery");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

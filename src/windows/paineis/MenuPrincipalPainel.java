package src.windows.paineis;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class MenuPrincipalPainel extends JPanel{
    public MenuPrincipalPainel(JPanel container, CardLayout layout){
        JButton botao = new JButton("Adicionar Obra");
        botao.addActionListener(e -> layout.show(container, "Adicionar Obra"));
        add(botao);
    }
}

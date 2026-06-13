package src.windows.paineis;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class MenuPrincipalPainel extends JPanel{
    public MenuPrincipalPainel(JPanel conteiner, CardLayout layout){

        JButton botaoPublicarObra = new JButton("Publicar Obra");
        botaoPublicarObra.addActionListener(e -> layout.show(conteiner, "Publicar Obra"));

        JButton botaoRemoverObra = new JButton("Remover Obra");
        botaoRemoverObra.addActionListener(e -> layout.show(conteiner, "Remover Obra"));

        JButton botaoAvaliarObra = new JButton("Avaliar Obra");
        botaoAvaliarObra.addActionListener(e -> layout.show(conteiner, "Avaliar Obra"));

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(botaoPublicarObra, gbc);

        gbc.gridy = 1;
        add(botaoRemoverObra, gbc);

        gbc.gridy = 2;
        add(botaoAvaliarObra, gbc);
    }
}

package src.windows.paineis;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.interfaces.IArtGallery;
import src.tiposObra.Obra;

public class ExibirTopObras extends JPanel{
    GridBagConstraints gbc;
    IArtGallery galeria;
    JButton botaoVoltar = new JButton("Voltar");
    JPanel container;
    CardLayout layout;

    public ExibirTopObras(JPanel container, CardLayout layout, IArtGallery galeria){
        this.container = container;
        this.layout = layout;
        this.galeria = galeria;

        this.botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> layout.show(container, "Menu Principal"));

        setLayout(new GridBagLayout());

        this.gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {

            removeAll();

            gbc.gridx = 2;
            gbc.gridy = 0;
            add(this.botaoVoltar, gbc);

            gbc.gridy = 1;
            gbc.gridx = 0;

            for(Obra obra : galeria.topObras()){
                JTextArea textoObra = new JTextArea(obra.exibirDetalhes());
                textoObra.setEditable(false);
                add(textoObra, gbc);

                gbc.gridx += 1;
                if(gbc.gridx > 4){
                    gbc.gridx = 0;
                    gbc.gridy += 1;
                }
            }
        }
    }
}

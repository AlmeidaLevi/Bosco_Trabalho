package src.windows.paineis;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Classe recebe o nome de dois campos e retorna um Painel contendo a label e o campo para ambos os nomes

public class PainelCamposVariaveis extends JPanel {
    private JTextField primeiroCampo;
    private JTextField segundoCampo;

    public PainelCamposVariaveis(JPanel container, CardLayout layout, String campo1, String campo2){
        JLabel primeiroTexto = new JLabel(campo1 + ": ");
        primeiroCampo = new JTextField(60);
        JLabel segundoTexto = new JLabel(campo2 + ": ");
        segundoCampo = new JTextField(60);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(primeiroTexto, gbc);

        gbc.gridx = 1;
        add(primeiroCampo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(segundoTexto, gbc);

        gbc.gridx = 1;

        add(segundoCampo, gbc);
    }

    public String getPrimeiroCampo(){
        return primeiroCampo.getText();
    }

    public String getSegundoCampo(){
        return segundoCampo.getText();
    }

}

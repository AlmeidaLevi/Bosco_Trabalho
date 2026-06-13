package src.windows.paineis;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.excecoes.ObraNaoEncontradaException;
import src.interfaces.IArtGallery;

public class RemoverObraPainel extends JPanel{
    private JLabel mensagemAviso = new JLabel("");
    private IArtGallery galeria;

    public RemoverObraPainel(JPanel container, CardLayout layout, IArtGallery galeria){
        this.galeria = galeria;
        JLabel labelTitulo = new JLabel("Titulo: ");
        JTextField campoTitulo = new JTextField(60);

        JLabel labelAutor = new JLabel("Autor: ");
        JTextField campoAutor = new JTextField(60);

        JButton botaoRemover = new JButton("Remover obra");
        botaoRemover.addActionListener(e -> this.RemoverObra(campoTitulo.getText(), campoAutor.getText()));

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> layout.show(container, "Menu Principal"));

        JPanel botoes = new JPanel();
        botoes.add(botaoVoltar);
        botoes.add(botaoRemover);


        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTitulo, gbc);

        gbc.gridx = 1;
        add(campoTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelAutor, gbc);

        gbc.gridx = 1;
        add(campoAutor, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(botoes, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(this.mensagemAviso, gbc);

    }

    public void RemoverObra(String titulo, String autor) {
        try {
            this.galeria.removerObra(titulo, autor);
            this.mensagemAviso.setText("A obra foi removida com sucesso!");
            this.mensagemAviso.setForeground(Color.GREEN);
        } catch( ObraNaoEncontradaException e){
            this.mensagemAviso.setText("A obra não foi encontrada, verifique se o titulo e o autor estão corretos!");
            this.mensagemAviso.setForeground(Color.RED);
        }
    }
}

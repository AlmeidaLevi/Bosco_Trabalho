package src.windows.paineis;

import java.util.Vector;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import src.interfaces.IArtGallery;
import src.tiposObra.Obra;

public class BuscarObrasPorAutorPainel extends JPanel{
    private IArtGallery galeria;
    private JLabel labelAutor;
    private JTextField campoAutor;
    private JButton botaoVoltar;
    private JButton botaoBuscar;
    private JLabel mensagemAviso;
    private JPanel painelObras;



    public BuscarObrasPorAutorPainel(JPanel container, CardLayout layout, IArtGallery galeria){
        this.galeria = galeria;

        this.labelAutor = new JLabel("Autor: ");
        this.campoAutor = new JTextField(60);

        this.botaoVoltar = new JButton("Voltar");
        this.botaoVoltar.addActionListener(e -> layout.show(container, "Menu Principal"));

        this.botaoBuscar = new JButton("Buscar");
        this.botaoBuscar.addActionListener(e -> this.buscar());

        JPanel botoes = new JPanel();
        botoes.add(botaoVoltar);
        botoes.add(botaoBuscar);

        this.mensagemAviso = new JLabel("");

        this.painelObras = new JPanel();
        this.painelObras.setLayout(new GridBagLayout());

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelAutor, gbc);

        gbc.gridx = 1;
        add(campoAutor, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(botoes, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(painelObras, gbc);

    }

    public void buscar(){
        this.mensagemAviso.setText("");
        this.painelObras.removeAll();
        Vector<Obra> obrasDoAutor = galeria.buscarPorAutor(this.campoAutor.getText());

        if(obrasDoAutor.isEmpty()){
            this.mensagemAviso.setText("Nenhuma obra encontrada.");
            this.mensagemAviso.setForeground(Color.RED);
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        for(Obra obra : obrasDoAutor){
            JTextArea textoObra = new JTextArea(obra.exibirDetalhes());
            textoObra.setEditable(false);
            this.painelObras.add(textoObra, gbc);

            gbc.gridx += 1;
            if(gbc.gridx > 4){
                gbc.gridx = 0;
                gbc.gridy += 1;
            }

        }
        this.painelObras.revalidate();
        this.painelObras.repaint();
    }
}

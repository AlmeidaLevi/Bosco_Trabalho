package src.windows.paineis;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import src.entidades.tiposObra.Obra;
import src.excecoes.ExposicaoNaoEncontradaException;
import src.interfaces.IArtGallery;

public class ExporObrasPainel extends JPanel {
    private IArtGallery galeria;
    private JLabel labelExposicao;
    private JTextField campoExposicao;
    private JButton botaoVoltar;
    private JButton botaoExporObras;
    private JLabel mensagemAviso;
    private JPanel painelObras;

    public ExporObrasPainel(JPanel conteiner, CardLayout layout, IArtGallery galeria){
        this.galeria = galeria;
        this.labelExposicao = new JLabel("Nome da exposição: ");
        this.campoExposicao = new JTextField(60);

        this.botaoVoltar = new JButton("Voltar");
        this.botaoVoltar.addActionListener(e -> layout.show(conteiner, "Menu Principal"));

        this.botaoExporObras = new JButton("Expor Obras");
        this.botaoExporObras.addActionListener(e -> this.expor());

        JPanel botoes = new JPanel();
        botoes.add(botaoVoltar);
        botoes.add(botaoExporObras);

        this.mensagemAviso = new JLabel("");

        this.painelObras = new JPanel();
        this.painelObras.setLayout(new GridBagLayout());

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelExposicao, gbc);

        gbc.gridx = 1;
        add(campoExposicao, gbc);

        gbc.gridy = 1;
        add(botoes, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(mensagemAviso , gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(painelObras, gbc);
    }

    public void expor(){
        this.mensagemAviso.setText("");
        this.painelObras.removeAll();
        String nomeExposicao = this.campoExposicao.getText();
        Vector<Obra> obrasExpostas;
        try{
            obrasExpostas = this.galeria.obrasExpostas(nomeExposicao);
        } catch(ExposicaoNaoEncontradaException e) {
            this.mensagemAviso.setText(e.getMessage());
            this.mensagemAviso.setForeground(Color.RED);
            return;
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;

        for(Obra obra : obrasExpostas){
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

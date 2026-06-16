package src.windows.paineis;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import src.entidades.tiposObra.Obra;
import src.excecoes.ExposicaoJaCadastradaException;
import src.interfaces.IArtGallery;
import src.entidades.Exposicao;

public class CriarExposicaoPainel extends JPanel{
    private JLabel labelExposicao;
    private JTextField campoExposicao;
    private JLabel labelObras;
    private JPanel painelObras;
    private IArtGallery galeria;
    private Vector<Obra> obrasSelecionadas = new Vector<>();
    private JLabel mensagemAviso;

    public CriarExposicaoPainel(JPanel container, CardLayout layout, IArtGallery galeria){
        this.galeria = galeria;
        this.labelExposicao = new JLabel("Nome da exposicao");
        this.campoExposicao = new JTextField(60);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> layout.show(container, "Menu Principal"));
        JButton botaoCriarExposicao = new JButton("Criar Exposicao");
        botaoCriarExposicao.addActionListener(e -> this.criarExposicao());

        JPanel botoes = new JPanel();
        botoes.add(botaoVoltar);
        botoes.add(botaoCriarExposicao);

        this.mensagemAviso = new JLabel("");

        this.labelObras = new JLabel("Selecione as obras a serem adicionadas na exposição.");
        this.painelObras = new JPanel();
        this.painelObras.setLayout(new GridBagLayout());

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(this.labelExposicao, gbc);

        gbc.gridx = 1;
        add(this.campoExposicao, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(botoes, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(this.mensagemAviso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(this.labelObras, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 100;
        add(this.painelObras, gbc);

    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);


        if (visible) {
            this.painelObras.removeAll();

            GridBagConstraints gbc_local = new GridBagConstraints();
            gbc_local.insets = new Insets(5, 5, 5, 5);
            gbc_local.gridy = 1;
            gbc_local.gridx = 0;

            for(Obra obra : galeria.listarObras()){
                JPanel painelObra = new JPanel();
                JTextArea textoObra = new JTextArea(obra.exibirDetalhes());
                textoObra.setEditable(false);
                painelObra.add(textoObra);

                JCheckBox selecionada = new JCheckBox();
                selecionada.addActionListener(e -> {
                    if(selecionada.isSelected()){
                        this.obrasSelecionadas.add(obra);
                    }else{
                        this.obrasSelecionadas.remove(obra);
                    }
                });
                painelObra.add(selecionada);

                this.painelObras.add(painelObra, gbc_local);

                gbc_local.gridx += 1;
                if(gbc_local.gridx > 4){
                    gbc_local.gridx = 0;
                    gbc_local.gridy += 1;
                }
            }
        }
        this.painelObras.revalidate();
        this.painelObras.repaint();
    }

    public void criarExposicao(){
        Exposicao exposicao = new Exposicao(this.campoExposicao.getText());

        for(Obra obra : this.obrasSelecionadas){
            exposicao.adicionarObra(obra);
        }

        try{
            this.galeria.adicionarExposicao(exposicao);

            this.mensagemAviso.setText("Exposição " + exposicao.getNome() + " criada com sucesso!!");
            this.mensagemAviso.setForeground(Color.GREEN);
        } catch (ExposicaoJaCadastradaException e){
            this.mensagemAviso.setText(e.getMessage());
            this.mensagemAviso.setForeground(Color.RED);
        }
    }

}

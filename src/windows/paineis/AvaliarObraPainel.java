package src.windows.paineis;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import src.excecoes.ObraNaoEncontradaException;
import src.interfaces.IArtGallery;
import src.tiposObra.Obra;
import src.utils.Avaliacao;


public class AvaliarObraPainel extends JPanel {
    private IArtGallery galeria;
    private JButton botaoVoltar;
    private JButton botaoAvaliar;
    private JLabel labelTitulo;
    private JTextField campoTitulo;
    private JLabel labelAutor;
    private JTextField campoAutor;
    private JLabel labelUsuario;
    private JTextField campoUsuario;
    private SpinnerNumberModel modeloNota;
    private JLabel labelNota;
    private JSpinner campoNota;
    private JLabel labelComentario;
    private JTextField campoComentario;
    private JLabel mensagemAviso;

    public AvaliarObraPainel(JPanel conteiner, CardLayout layout, IArtGallery galeria){
        this.galeria = galeria;
        this.botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> layout.show(conteiner, "Menu Principal"));

        this.botaoAvaliar = new JButton("Avaliar");
        botaoAvaliar.addActionListener(e -> this.avaliarObra());

        // Painel pra manter os botões organizados
        JPanel botoes = new JPanel();
        botoes.add(botaoVoltar);
        botoes.add(botaoAvaliar);

        this.labelTitulo = new JLabel("Titulo da obra: ");
        this.campoTitulo = new JTextField(60);

        this.labelAutor = new JLabel("Autor da obra: ");
        this.campoAutor = new JTextField(60);

        this.labelUsuario = new JLabel("Nome do usuario: ");
        this.campoUsuario = new JTextField(60);

        this.labelNota = new JLabel("Nota da obra: ");
        this.modeloNota = new SpinnerNumberModel(
            1,   // valor inicial
            1,   // mínimo
            10,  // máximo
            1    // incremento
        );

        this.campoNota = new JSpinner(this.modeloNota);
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) campoNota.getEditor();
        editor.getTextField().setEditable(false);

        this.labelComentario = new JLabel("Comentario da avaliação: ");
        this.campoComentario = new JTextField(60);

        this.mensagemAviso = new JLabel("");

        // Ajustando o layout
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(this.labelTitulo, gbc);

        gbc.gridx = 1;
        add(this.campoTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(this.labelAutor, gbc);

        gbc.gridx = 1;
        add(this.campoAutor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(this.labelUsuario, gbc);

        gbc.gridx = 1;
        add(this.campoUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(this.labelNota, gbc);

        gbc.gridx = 1;
        add(this.campoNota, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(this.labelComentario, gbc);

        gbc.gridx = 1;
        add(this.campoComentario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(botoes, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(this.mensagemAviso, gbc);



    }

    public void avaliarObra(){
        String textoTitulo = this.campoTitulo.getText();
        String textoAutor = this.campoAutor.getText();
        Obra obra = galeria.buscarObra(textoTitulo, textoAutor);
        String usuario = this.campoUsuario.getText();

        // o getValue do JSppiner retorna um object, então é necessario o cast para integer
        int nota = (Integer) this.campoNota.getValue();

        String comentario = this.campoComentario.getText();
        Avaliacao avaliacao = new Avaliacao(usuario, nota, comentario);

        try{
            this.galeria.avaliarObra(obra, avaliacao);
            this.mensagemAviso.setText("Obra avaliada com sucesso!!");
            this.mensagemAviso.setForeground(Color.GREEN);
        } catch (ObraNaoEncontradaException | NullPointerException e) {
            this.mensagemAviso.setText("Obra " + textoTitulo + " do autor " + textoAutor + " não encontrada!!!" + e.getMessage());
            this.mensagemAviso.setForeground(Color.RED);
        }

    }
}

package src.windows.paineis;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.excecoes.ObraJaCadastradaException;
import src.interfaces.IArtGallery;
import src.tiposObra.ArteGenerativa;
import src.tiposObra.Modelagem3D;
import src.tiposObra.Obra;
import src.tiposObra.PinturaDigital;

public class PublicarObraPainel extends JPanel{
    private IArtGallery galeria;

    private JButton botaoVoltar;
    private JButton botaoAdicionar;

    private JLabel labelTitulo;
    private JTextField campoTitulo;
    private JLabel labelAutor;
    private JTextField campoAutor;
    private JLabel mensagemAviso;

    private JLabel labelCategoria;
    private JComboBox<String> campoCategoria;

    private JPanel painelPinturaDigital;
    private JPanel painelModelagem3D;
    private JPanel painelArteGenerativa;

    public PublicarObraPainel(JPanel conteiner, CardLayout layout, IArtGallery galeria){
        this.galeria = galeria;

        // Criando os botões
        this.botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> layout.show(conteiner, "Menu Principal"));

        botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(e -> this.adicionarObra());

        JPanel botoes = new JPanel();
        botoes.add(botaoVoltar);
        botoes.add(botaoAdicionar);

        // Criando componentes do formulario
        this.labelTitulo = new JLabel("Titulo: ");
        this.campoTitulo = new JTextField(60);
        this.labelAutor = new JLabel("Autor: ");
        this.campoAutor = new JTextField(60);

        // label responsavel por avisar se a obra foi cadastrada
        this.mensagemAviso = new JLabel("");

        // Criando campo categorias
        String[] categorias = {
            "Pintura Digital",
            "Modelagem3D",
            "Arte Generativa"
        };

        this.labelCategoria = new JLabel("Categoria da obra: ");
        this.campoCategoria = new JComboBox<>(categorias);

        // Criando paineis persoanlizados que aparecerão ao selecionar uma categoria
        CardLayout layoutPaineisPersonalizados = new CardLayout();

        this.painelPinturaDigital = new PainelCamposVariaveis(conteiner, layoutPaineisPersonalizados, "Resolução", "Software");
        this.painelModelagem3D = new PainelCamposVariaveis(conteiner, layoutPaineisPersonalizados, "Numero de poligonos", "Engine");
        this.painelArteGenerativa = new PainelCamposVariaveis(conteiner, layoutPaineisPersonalizados, "Seed", "Algoritmo");

        JPanel paineisPersonalizados = new JPanel(layoutPaineisPersonalizados);
        paineisPersonalizados.add(painelPinturaDigital, "Pintura Digital");
        paineisPersonalizados.add(painelModelagem3D, "Modelagem3D");
        paineisPersonalizados.add(painelArteGenerativa, "Arte Generativa");

        // Configurando ações de botões que necessitava da criação de outras instancias

        // Ação que mudam os dois ultimos campos dependendo da categoria selacionada
        campoCategoria.addActionListener(e -> {
            layoutPaineisPersonalizados.show(
            paineisPersonalizados,
            (String) campoCategoria.getSelectedItem()
            );
        });



        // Ajustando o layout
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelCategoria, gbc);

        gbc.gridx = 1;
        add(campoCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(paineisPersonalizados, gbc);


        gbc.gridx = 1;
        gbc.gridy = 5;
        add(botoes, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(mensagemAviso, gbc);

    }

    public void adicionarObra(){
        Obra new_obra = null;
        String textoTitulo = this.campoTitulo.getText();
        String textoAutor = this.campoAutor.getText();
        String categoria_atual = (String) this.campoCategoria.getSelectedItem();

        try{

            switch (categoria_atual) {
                case "Pintura Digital" -> new_obra = new PinturaDigital(textoTitulo,
                    textoAutor,
                    ((PainelCamposVariaveis) this.painelPinturaDigital).getPrimeiroCampo(),
                    ((PainelCamposVariaveis) this.painelPinturaDigital).getSegundoCampo());
                case "Modelagem3D" -> new_obra = new Modelagem3D(textoTitulo,
                    textoAutor,
                    Integer.parseInt(((PainelCamposVariaveis) this.painelModelagem3D).getPrimeiroCampo()),
                    ((PainelCamposVariaveis) this.painelModelagem3D).getSegundoCampo());
                case "Arte Generativa" -> new_obra = new ArteGenerativa(textoTitulo,
                            textoAutor,
                            Integer.parseInt(((PainelCamposVariaveis) this.painelArteGenerativa).getPrimeiroCampo()),
                            ((PainelCamposVariaveis) this.painelArteGenerativa).getSegundoCampo());
            }

        } catch (NumberFormatException exception){
            if ("Modelagem3D".equals(categoria_atual)){
                this.mensagemAviso.setText("O campo 'Numero de poligonos' só aceita numeros como entrada!!");
                this.mensagemAviso.setForeground(Color.RED);
                return;
            }

            if ("Arte Generativa".equals(categoria_atual)){
                this.mensagemAviso.setText("O campo 'Seed' só aceita numeros como entrada!!");
                this.mensagemAviso.setForeground(Color.RED);
                return;

            }
        }
        try {
            this.galeria.publicarObra(new_obra);
            mensagemAviso.setText("OBRA CADASTRADA COM SUCESSO!!!!!!");
            this.mensagemAviso.setForeground(Color.GREEN);

        } catch (ObraJaCadastradaException exception) {
            mensagemAviso.setText("OBRA JÁ FOI CADASTRADA ANTERIORMENTE!!!!!!");
            this.mensagemAviso.setForeground(Color.RED);
        }
  }
}

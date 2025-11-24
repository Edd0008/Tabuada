package br.senai.jandira.tabuada.ui;

import br.senai.jandira.tabuada.model.Tabuada;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;


public class TelaTabuada extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // --- CONFIGURAÇÕES DA JANELA ---
        stage.setHeight(500);
        stage.setTitle("Tabuada");
        stage.setResizable(false);

        // Layout principal (vertical)
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #F5F5F5;");
        Scene scene = new Scene(root);

        // --- CABEÇALHO ---
        VBox header = new VBox();
        header.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #DDDDDD; -fx-border-width: 0 0 1 0;");

        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setPadding(new Insets(12, 0, 0, 12));
        labelTitulo.setStyle("-fx-font-size: 28; -fx-font-weight: bold; -fx-text-fill: #2A4E96;");

        Label labelSubtitulo = new Label("Crie a tabuada desejada");
        labelSubtitulo.setPadding(new Insets(0, 0, 12, 12));
        labelSubtitulo.setStyle("-fx-text-fill: #555555;");

        header.getChildren().addAll(labelTitulo, labelSubtitulo);

        // --- FORMULÁRIO (GridPane) ---
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15));

        // Labels e campos
        Label labelMultiplicando = new Label("Multiplicando:");
        TextField textFieldMultilicando = new TextField();

        Label labelMenorMultiplicador = new Label("Menor Multiplicador:");
        TextField textFieldMenorMultiplicador = new TextField();

        Label labelMaiorMultiplicador = new Label("Maior Multiplicador:");
        TextField textFieldMaiorMultiplicador = new TextField();

        // Estilo das labels
        labelMultiplicando.setStyle("-fx-font-weight: bold;");
        labelMenorMultiplicador.setStyle("-fx-font-weight: bold;");
        labelMaiorMultiplicador.setStyle("-fx-font-weight: bold;");

        // Adicionando ao Grid
        gridPane.add(labelMultiplicando, 0, 0);
        gridPane.add(textFieldMultilicando, 1, 0);
        gridPane.add(labelMenorMultiplicador, 0, 1);
        gridPane.add(textFieldMenorMultiplicador, 1, 1);
        gridPane.add(labelMaiorMultiplicador, 0, 2);
        gridPane.add(textFieldMaiorMultiplicador, 1, 2);

        // --- BOTÕES ---
        HBox boxBotoes = new HBox(10);
        boxBotoes.setPadding(new Insets(10, 15, 10, 15));
        boxBotoes.setStyle("-fx-background-color: #FFFFFF;" +
                " -fx-border-color: #DDDDDD; -fx-border-width: 1 0 1 0;");

        Button botaoCalcular = new Button("Calcular");
        Button botaoLimpar = new Button("Limpar");
        Button botaoSair = new Button("Sair");


        boxBotoes.getChildren().addAll(botaoCalcular, botaoLimpar, botaoSair);

        // --- ÁREA DE RESULTADOS ---
        VBox boxResultados = new VBox();
        boxResultados.setPadding(new Insets(10));

        Label labelResultados = new Label("Resultados:");
        labelResultados.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #333333;");

        ListView listaTabuada = new ListView();
        listaTabuada.setPadding(new Insets(5));

        boxResultados.getChildren().addAll(labelResultados, listaTabuada);

        // --- ADICIONAR TUDO NA ROOT ---
        root.getChildren().addAll(header, gridPane, boxBotoes, boxResultados);

        stage.setScene(scene);
        stage.show();

        // --- AÇÕES DOS BOTÕES ---

        // Botão Calcular
        botaoCalcular.setOnAction(e -> {
            Tabuada tabuada = new Tabuada();

            // Pegando valores dos inputs
            int multiplicando = Integer.parseInt(textFieldMultilicando.getText());
            tabuada.multiplicando = multiplicando;

            tabuada.multiplicadorInicial = Integer.parseInt(textFieldMenorMultiplicador.getText());
            tabuada.multiplicadorFinal = Integer.parseInt(textFieldMaiorMultiplicador.getText());

            // Gerando tabuada e exibindo
            String[] resultado = tabuada.calcularTabuada();
            listaTabuada.getItems().addAll(resultado);
        });

        // Botão Limpar (corrigido)
        botaoLimpar.setOnAction(e -> {

            // Caminho do arquivo
            Path arquivo = Path.of("c:\\Users\\25203664\\DS1T\\Tabuada\\dados_tabuada.csv");

            // Montar dados
            String dados = textFieldMultilicando.getText() + ";" +
                    textFieldMenorMultiplicador.getText() + ";" +
                    textFieldMaiorMultiplicador.getText() + ";" +
                    LocalDateTime.now() + "\n";

            // Salvar no arquivo
            try {
                Files.writeString(arquivo, dados,
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException erro) {
                System.out.println(erro.getMessage());
            }

            // Limpar a tela
            textFieldMaiorMultiplicador.clear();
            textFieldMenorMultiplicador.clear();
            textFieldMultilicando.clear();
            listaTabuada.getItems().clear();
            textFieldMultilicando.requestFocus();
        });

        // Botão Sair
        botaoSair.setOnAction(e -> stage.close());


    }
}

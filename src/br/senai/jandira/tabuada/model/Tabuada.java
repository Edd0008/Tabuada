package br.senai.jandira.tabuada;

import java.util.Scanner;

public class Tabuada {
    public int multiplicando;
    public int multiplicadorInicial;
    public int multiplicadorFinal;
    public String[] tabuada;

    public void obterDados() {
        Scanner leitor = new Scanner(System.in);

        System.out.print("Escolha o valor do multiplicando: ");
        multiplicando = leitor.nextInt();

        System.out.print("Escolha o valor do multiplicador INICIAL: ");
        multiplicadorInicial = leitor.nextInt();

        System.out.print("Escolha o valor do multiplicador FINAL: ");
        multiplicadorFinal = leitor.nextInt();

        calcularTabuada();

    }

    public void calcularTabuada() {
        int apoio = 0;
        if (multiplicadorFinal < multiplicadorInicial) {
            apoio = multiplicadorFinal;
            multiplicadorFinal = multiplicadorInicial;
            multiplicadorInicial = apoio;
        }

        int tamanhoVetor = multiplicadorFinal - multiplicadorInicial + 1;
        tabuada = new String[tamanhoVetor];

        int i = 0;
        while (i < tamanhoVetor) {
            int produto = multiplicando * multiplicadorInicial;
            tabuada[i] = multiplicando + " X " + multiplicadorInicial + " = " + produto;
            multiplicadorInicial = multiplicadorInicial + 1;
            i = i + 1;
        }
        exibirTabuada();
    }
    
    public void exibirTabuada(){
        System.out.println("Resultado da sua tabuada");

        int i = 0;
        while (i < tabuada.length){
            System.out.println(tabuada[i]);
            i++; // i = i + 1
        }

    }
}
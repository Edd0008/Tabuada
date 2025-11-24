package br.senai.jandira.tabuada.model;

import br.senai.jandira.tabuada.Tabuada;

public class TabuadaApp {
    public static void main(String[] args) {
        System.out.println("==================");
        System.out.println("Tabuada");
        System.out.println("==================");

        br.senai.jandira.tabuada.Tabuada tabuada = new Tabuada();
        tabuada.obterDados();

    }
}

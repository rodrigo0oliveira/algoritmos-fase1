package com.algoritmos;

import static com.algoritmos.Guloso.guloso;

public class Main {
    public static void main(String[] args) {
        String entradaProjeto = """
                {"custos": [[2, 1, 9, 9, 9],
                [3, null, 1, 1, 9],
                [1, 1, 1, null, 2],
                [9, 9, 1, 1, 1]]}
                """;


        String entradaCriada = """
                {"custos": [[1, 2, 9, 9],
                            [3, 9, 9, 9],
                            [1, 1, 1, 1],
                            [9, 9, 9, 1]]}
                """;

        String grade2x3 = """
                {"custos": [[1, 1, 1],
                            [1, 1, 1]]}
                """;

        String grade3x3 = """
                {"custos": [[1, 1, 1],
                            [1, 1, 1],
                            [1, 1, 1]]}
                """;

        String grade6x6 = """
                {"custos": [[1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1]]}
                """;

        String grade10x10 = """
                {"custos": [[1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]]}
                """;

        String grade1x10 = """
                {"custos": [[1, 1, 1, 1, 1, 1, 1, 1, 1, 1]]}
                """;


        System.out.println("Algoritmo recursivo com entrada do projeto");
        Recursivo.resolver(entradaProjeto);

        System.out.println("===========================");

        System.out.println("Algoritmo guloso com entrada do projeto");
        guloso(entradaProjeto);

        System.out.println("===========================");

        System.out.println("Algoritmo recursivo com entrada criada");
        Recursivo.resolver(entradaCriada);

        System.out.println("===========================");
        System.out.println("Algoritmo guloso com entrada criada");
        guloso(entradaCriada);

        System.out.println("===========================");
        System.out.println("Medição 2 x 3 (esperado: 9 chamadas)");
        Recursivo.chamadas = 0;
        Recursivo.resolver(grade2x3);

        System.out.println("===========================");
        System.out.println("Medição 3 x 3 (esperado: 19 chamadas)");
        Recursivo.chamadas = 0;
        Recursivo.resolver(grade3x3);

        System.out.println("===========================");
        System.out.println("Medição 6 x 6 (esperado: 923 chamadas)");
        Recursivo.chamadas = 0;
        Recursivo.resolver(grade6x6);

        System.out.println("===========================");
        System.out.println("Medição 10 x 10 (esperado: 184.755 chamadas)");
        Recursivo.chamadas = 0;
        Recursivo.resolver(grade10x10);

        System.out.println("===========================");
        System.out.println("Medição 1 x 10 (esperado: 10 chamadas)");
        Recursivo.chamadas = 0;
        Recursivo.resolver(grade1x10);

        System.out.println("===========================");
        System.out.println("Medição grade do exemplo");
        Recursivo.chamadas = 0;
        Recursivo.resolver(entradaProjeto);
    }
}
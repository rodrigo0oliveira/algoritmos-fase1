package com.algoritmos;

import com.algoritmos.entidades.ResolverResponse;
import com.algoritmos.entidades.StatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Recursivo {

    private Recursivo() {
    }

    public static long chamadas = 0;

    public static ResolverResponse resolver(String entrada) {
        try {
            ResolverResponse response = JsonLeitor.lerEntrada(entrada);
            if (!StatusResponse.OK.getDescricao().equals(response.getStatus())) {
                return response;
            }

            Integer custo = recursivo(response.getMatriz(), 0, 0);

            if (custo == null) {
                response.setStatus(StatusResponse.SEM_CAMINHO.getDescricao());
            }

            response.setCusto(custo);

            System.out.println("Total de chamadas recursivas : " + chamadas);
            System.out.println(response);
            return response;
        } catch (JsonProcessingException e) {
            return new ResolverResponse(StatusResponse.ENTRADA_INVALIDA.getDescricao(), "Erro ao processar JSON", null, null);
        }
    }

    public static Integer recursivo(Integer[][] custos, int linha, int coluna) {

        chamadas++;

        int lengthLinha = custos.length - 1;
        int lengthColuna = custos[0].length - 1;

        if (linha > lengthLinha || coluna > lengthColuna
        ) {
            return null;
        }

        if (custos[linha][coluna] == null) {
            return null;
        }

        if (linha == lengthLinha && coluna == lengthColuna) {
            return custos[linha][coluna];
        }

        var direita = recursivo(custos, linha, coluna + 1);
        var baixo = recursivo(custos, linha + 1, coluna);

        if (direita == null && baixo == null) {
            return null;
        }

        if (direita == null) {
            return custos[linha][coluna] + baixo;
        } else if (baixo == null) {
            return custos[linha][coluna] + direita;
        }

        Integer menor = direita > baixo ? baixo : direita;

        return custos[linha][coluna] + menor;
    }
}

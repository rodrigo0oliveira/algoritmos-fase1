package com.algoritmos;

import com.algoritmos.entidades.ResolverResponse;
import com.algoritmos.entidades.StatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Guloso {

    private Guloso() {
    }

    public static ResolverResponse guloso(String entrada) {
        try {
            ResolverResponse response = JsonLeitor.lerEntrada(entrada);
            if (!StatusResponse.OK.getDescricao().equals(response.getStatus())) {
                return response;
            }

            Integer custo = guloso(response.getMatriz());

            if (custo == null) {
                response.setStatus(StatusResponse.SEM_CAMINHO.getDescricao());
            }

            response.setCusto(custo);

            System.out.println(response);
            return response;
        } catch (JsonProcessingException e) {
            return new ResolverResponse(StatusResponse.ENTRADA_INVALIDA.getDescricao(), "Erro ao processar JSON", null, null);
        }
    }

    public static Integer guloso(Integer[][] custos) {
        int total = custos[0][0];
        int ultimaLinha = custos.length - 1;
        int ultimaColuna = custos[0].length - 1;
        int linha = 0;
        int coluna = 0;

        while (linha <= ultimaLinha && coluna <= ultimaColuna) {
            if (linha == ultimaLinha && coluna == ultimaColuna) {
                return total;
            }

            var posicao = custos[linha][coluna];

            if (posicao == null) {
                return null;
            }

            if (coluna == ultimaColuna) {
                linha++;
                var proxPosicao = custos[linha][coluna];
                if (proxPosicao == null) {
                    return null;
                }

                total += custos[linha][coluna];
                continue;
            }
            if (linha == ultimaLinha) {
                coluna++;
                var proxPosicao = custos[linha][coluna];
                if (proxPosicao == null) {
                    return null;
                }
                total += custos[linha][coluna];
                continue;
            }


            var direita = custos[linha][coluna + 1];
            var baixo = custos[linha + 1][coluna];

            if (direita == null && baixo == null) {
                return null;
            }

            if (direita == null) {
                total += baixo;
                linha++;
                continue;
            } else if (baixo == null) {
                total += direita;
                coluna++;
                continue;
            }

            if (direita <= baixo) {
                total += direita;
                coluna++;
                continue;
            }

            total += baixo;
            linha++;

        }

        return total;
    }
}

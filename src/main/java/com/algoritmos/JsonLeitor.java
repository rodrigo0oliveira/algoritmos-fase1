package com.algoritmos;

import com.algoritmos.entidades.ResolverResponse;
import com.algoritmos.entidades.StatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonLeitor {

    private JsonLeitor() {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ResolverResponse lerEntrada(String entrada) throws JsonProcessingException {
        JsonNode node = MAPPER.readTree(entrada);

        if (node == null || !node.isObject() || !node.has("custos")) {
            return new ResolverResponse(StatusResponse.ENTRADA_INVALIDA.getDescricao(), "Objeto inválido", null, null);
        }

        JsonNode custos = node.get("custos");

        if (!custos.isArray() || custos.size() == 0) {
            return new ResolverResponse(StatusResponse.ENTRADA_INVALIDA.getDescricao(), "Matriz vazia", null, null);
        }

        int numLinhas = custos.size();
        int numColunas = custos.get(0).size();
        Integer[][] matriz = new Integer[numLinhas][];

        for (int i = 0; i < numLinhas; i++) {
            JsonNode linha = custos.get(i);
            if (!linha.isArray() || linha.size() == 0 || linha.size() != numColunas) {
                return new ResolverResponse(StatusResponse.ENTRADA_INVALIDA.getDescricao(), "Tamanho de linhas e colunas diferentes", null, null);
            }

            matriz[i] = new Integer[numColunas];
            for (int j = 0; j < numColunas; j++) {
                JsonNode celula = linha.get(j);
                if (celula.isNull()) {
                    matriz[i][j] = null;
                } else if (celula.isInt() && celula.intValue() > 0) {
                    matriz[i][j] = celula.intValue();
                } else {
                    return new ResolverResponse(StatusResponse.ENTRADA_INVALIDA.getDescricao(), "Valores inválidos", null, null);
                }
            }
        }
        return new ResolverResponse(StatusResponse.OK.getDescricao(), "", matriz, null);
    }
}

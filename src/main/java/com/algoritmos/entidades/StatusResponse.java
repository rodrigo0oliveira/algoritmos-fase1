package com.algoritmos.entidades;

public enum StatusResponse {
    OK("OK"),
    ENTRADA_INVALIDA("entrada_inválida"),
    SEM_CAMINHO("sem_caminho");

    private final String descricao;

    StatusResponse(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

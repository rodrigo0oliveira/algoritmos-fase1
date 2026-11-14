package com.algoritmos.entidades;

public class ResolverResponse {

    private String status;
    private String mensagem;
    private Integer[][] matriz;
    private Integer custo;

    public ResolverResponse(String status, String mensagem, Integer[][] matriz, Integer custo) {
        this.status = status;
        this.mensagem = mensagem;
        this.matriz = matriz;
        this.custo = custo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }


    public Integer[][] getMatriz() {
        return matriz;
    }


    public Integer getCusto() {
        return custo;
    }

    public void setCusto(Integer custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        return
                "Status='" + status + '\'' +
                        ", mensagem='" + mensagem + '\'' +
                        ", custo=" + custo;
    }
}

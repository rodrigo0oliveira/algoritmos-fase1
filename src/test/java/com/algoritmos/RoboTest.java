package com.algoritmos;

import com.algoritmos.entidades.ResolverResponse;
import com.algoritmos.entidades.StatusResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoboTest {

    private static final String OK = StatusResponse.OK.getDescricao();
    private static final String SEM_CAMINHO = StatusResponse.SEM_CAMINHO.getDescricao();
    private static final String ENTRADA_INVALIDA = StatusResponse.ENTRADA_INVALIDA.getDescricao();

    private static final Integer[][] EXEMPLO = {
            {2, 1, 9, 9, 9},
            {3, null, 1, 1, 9},
            {1, 1, 1, null, 2},
            {9, 9, 1, 1, 1}
    };

    private static final Integer[][] GRADE_CRIADA = {
            {1, 2, 9, 9},
            {3, 9, 9, 9},
            {1, 1, 1, 1},
            {9, 9, 9, 1}
    };


    private static void assertOk(String json, int custoEsperado) {
        ResolverResponse r = Recursivo.resolver(json);
        assertEquals(OK, r.getStatus(), json);
        assertEquals(custoEsperado, r.getCusto(), json);
    }

    private static void assertSemCaminho(String json) {
        ResolverResponse r = Recursivo.resolver(json);
        assertEquals(SEM_CAMINHO, r.getStatus(), json);
        assertNull(r.getCusto(), json);
    }

    private static void assertInvalida(String json) {
        ResolverResponse r = Recursivo.resolver(json);
        assertEquals(ENTRADA_INVALIDA, r.getStatus(), json);
        assertNull(r.getCusto(), json);
        assertNotNull(r.getMensagem(), json);
    }


    @Test
    void givenUmaCasa_WhenRecursivo_ShouldReturnOk() {
        assertOk("{\"custos\": [[7]]}", 7);
    }

    @Test
    void givenUmaLinha_WhenRecursivo_ShouldReturnOk() {
        assertOk("{\"custos\": [[1, 2, 3]]}", 6);
    }

    @Test
    void givenUmaColuna_WhenRecursivo_ShouldReturnOk() {
        assertOk("{\"custos\": [[1], [2], [3]]}", 6);
    }

    @Test
    void givenGrade2x3TodaCusto1_WhenRecursivo_ShouldReturnOk() {
        assertOk("{\"custos\": [[1, 1, 1], [1, 1, 1]]}", 4);
    }

    @Test
    void givenGradeDoExemplo_WhenRecursivo_ShouldReturnOk() {
        assertOk("{\"custos\": [[2, 1, 9, 9, 9], [3, null, 1, 1, 9], "
                + "[1, 1, 1, null, 2], [9, 9, 1, 1, 1]]}", 11);
    }

    @Test
    void givenObstaculosComRota_WhenRecursivo_ShouldReturnOk() {
        assertOk("{\"custos\": [[1, 1, null], [2, null, 1], [1, 1, 1]]}", 6);
    }


    @Test
    void givenUnicaCasaObstaculo_WhenRecursivo_ShouldReturnSemCaminho() {
        assertSemCaminho("{\"custos\": [[null]]}");
    }

    @Test
    void givenPartidaBloqueada_WhenRecursivo_ShouldReturnSemCaminho() {
        assertSemCaminho("{\"custos\": [[null, 1], [1, 1]]}");
    }

    @Test
    void givenChegadaBloqueada_WhenRecursivo_ShouldReturnSemCaminho() {
        assertSemCaminho("{\"custos\": [[1, 1], [1, null]]}");
    }

    @Test
    void givenDiagonalBloqueada_WhenRecursivo_ShouldReturnSemCaminho() {
        assertSemCaminho("{\"custos\": [[1, null], [null, 1]]}");
    }


    @Test
    void givenListaVazia_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"custos\": []}");
    }

    @Test
    void givenLinhaVazia_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"custos\": [[]]}");
    }

    @Test
    void givenLinhasDeTamanhosDiferentes_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"custos\": [[1, 2], [3]]}");
    }

    @Test
    void givenValorBooleano_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"custos\": [[true, 1]]}");
    }

    @Test
    void givenValorTexto_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"custos\": [[\"1\", 1]]}");
    }

    @Test
    void givenValorDecimal_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"custos\": [[1.5, 1]]}");
    }

    @Test
    void givenValorZero_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"custos\": [[0, 1]]}");
    }

    @Test
    void givenValorNegativo_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"custos\": [[-1, 1]]}");
    }

    @Test
    void givenChaveErrada_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"matriz\": [[1]]}");
    }

    @Test
    void givenListaSemChave_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("[[1, 2]]");
    }

    @Test
    void givenJsonQuebrado_WhenRecursivo_ShouldReturnEntradaInvalida() {
        assertInvalida("{\"custos\": [[1, 2]");
    }

    @Test
    void givenGrade4x4OndeGulosoErra_WhenRecursivo_ShouldReturnOk() {
        assertOk("{\"custos\": [[1, 2, 9, 9], [3, 9, 9, 9], "
                + "[1, 1, 1, 1], [9, 9, 9, 1]]}", 9);
    }

    @Test
    void givenColunaBloqueadaNoMeio_WhenRecursivo_ShouldReturnSemCaminho() {
        assertSemCaminho("{\"custos\": [[5], [null], [2]]}");
    }

    @Test
    void givenUnicoCaminhoPelaDireita_WhenRecursivo_ShouldReturnOk() {
        assertOk("{\"custos\": [[1, 2], [null, 3]]}", 6);
    }

    @Test
    void givenMatrizDoExemplo_WhenRecursivoComPosicao_ShouldReturn11() {
        Integer[][] g = {
                {2, 1, 9, 9, 9},
                {3, null, 1, 1, 9},
                {1, 1, 1, null, 2},
                {9, 9, 1, 1, 1}
        };
        assertEquals(11, Recursivo.recursivo(g, 0, 0));
    }

    @Test
    void givenGradeDoExemplo_WhenGuloso_ShouldReturn26() {
        assertEquals(26, Guloso.guloso(EXEMPLO));
    }

    @Test
    void givenGradeDoExemplo_WhenRecursivo_ShouldReturn11MenorQueGuloso() {
        assertEquals(11, Recursivo.recursivo(EXEMPLO, 0, 0));
    }

    @Test
    void givenGradeDoGrupo_WhenGuloso_ShouldReturn32() {
        assertEquals(32, Guloso.guloso(GRADE_CRIADA));
    }

    @Test
    void givenGradeDoGrupo_WhenRecursivo_ShouldReturn9() {
        assertEquals(9, Recursivo.recursivo(GRADE_CRIADA, 0, 0));
    }

    @Test
    void givenUmaCasa_WhenGuloso_ShouldReturnCustoDaCasa() {
        assertEquals(7, Guloso.guloso(new Integer[][]{{7}}));
    }

    @Test
    void givenUmaLinha_WhenGuloso_ShouldSomarTudo() {
        assertEquals(6, Guloso.guloso(new Integer[][]{{1, 2, 3}}));
    }

    @Test
    void givenUmaColuna_WhenGuloso_ShouldSomarTudo() {
        assertEquals(6, Guloso.guloso(new Integer[][]{{1}, {2}, {3}}));
    }

    @Test
    void givenEmpate_WhenGuloso_ShouldPreferirDireita() {
        Integer[][] g = {{1, 2, 5}, {2, 9, 1}};
        assertEquals(9, Guloso.guloso(g));
    }

    @Test
    void givenBecoSemSaida_WhenGuloso_ShouldReturnNullMesmoHavendoRota() {
        Integer[][] g = {{1, 1, null}, {2, null, 1}, {1, 1, 1}};
        assertNull(Guloso.guloso(g));
        assertEquals(6, Recursivo.recursivo(g, 0, 0));
    }
}
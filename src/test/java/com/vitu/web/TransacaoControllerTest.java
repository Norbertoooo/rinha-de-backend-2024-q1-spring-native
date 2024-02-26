package com.vitu.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitu.dto.request.TransacaoRequestDto;
import com.vitu.exception.OperacaoNaoSuportadaException;
import com.vitu.factory.TransacaoRequestFactory;
import com.vitu.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransacaoController.class)
class TransacaoControllerTest {

    String URI = "/clientes/";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TransacaoService transacaoService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void criarNovaTransacao() throws Exception {

        TransacaoRequestDto transacaoRequestDto = TransacaoRequestFactory.construirTransacaoRequestComValorNegatico();

        String stringJson = objectMapper.writeValueAsString(transacaoRequestDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI.concat("1").concat("/transacoes"))
                .content(stringJson)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void deveRetornarErroQuandoSolicitarNovaTransacaoComTipoInvalido() throws Exception {

        TransacaoRequestDto transacaoRequestDto = TransacaoRequestFactory.construirTransacaoRequestComTipoInvalido();

        when(transacaoService.efetuarTransacao(1L, transacaoRequestDto))
                .thenThrow(OperacaoNaoSuportadaException.class);

        String stringJson = objectMapper.writeValueAsString(transacaoRequestDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI.concat("1").concat("/transacoes"))
                .content(stringJson)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void deveRetornarErroQuandoSolicitarNovaTransacaoComDescricaoInvalida() throws Exception {

        TransacaoRequestDto transacaoRequestDto = TransacaoRequestFactory.construirTransacaoRequestComDescricaoinvalida();

        String stringJson = objectMapper.writeValueAsString(transacaoRequestDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI.concat("1").concat("/transacoes"))
                .content(stringJson)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void deveRetornarErroQuandoSolicitarNovaTransacaoComDescricaoVazia() throws Exception {

        TransacaoRequestDto transacaoRequestDto = TransacaoRequestFactory.construirTransacaoRequestComDescricaoVazia();

        String stringJson = objectMapper.writeValueAsString(transacaoRequestDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI.concat("1").concat("/transacoes"))
                .content(stringJson)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

}
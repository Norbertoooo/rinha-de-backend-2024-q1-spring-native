package com.vitu.web;

import com.vitu.dto.response.ExtratoResponseDto;
import com.vitu.exception.ClienteNaoEncontradoException;
import com.vitu.service.ExtratoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExtratoController.class)
class ExtratoControllerTest {

    String URI = "/clientes";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ExtratoService extratoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void deveRetornarClienteNaoEncontradoQuandoSolicitarExtrato() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequest = MockMvcRequestBuilders.get(URI.concat("/1/extrato"));

        when(extratoService.obterExtratoPorClienteId(1L)).thenThrow(ClienteNaoEncontradoException.class);

        mockMvc.perform(mockHttpServletRequest)
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    void deveRetornarExtratoComSucessoQuandoSolicitarExtrato() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI.concat("/1/extrato"));

        when(extratoService.obterExtratoPorClienteId(1L))
                .thenReturn(ExtratoResponseDto.builder().build());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("ultimas_transacoes").hasJsonPath());
    }

    @Test
    void deveRetornar422QuandoSolicitarExtratoComUrlErrada() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI.concat("/1/extratos"));

        when(extratoService.obterExtratoPorClienteId(1L))
                .thenReturn(ExtratoResponseDto.builder().build());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
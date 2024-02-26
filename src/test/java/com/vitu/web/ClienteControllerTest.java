package com.vitu.web;

import com.vitu.exception.ClienteNaoEncontradoException;
import com.vitu.service.ClienteService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    String URI = "/clientes";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClienteService clienteService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void deveRetornarClienteNaoEncontradoQuandoSolicitarClientePorId() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequest = MockMvcRequestBuilders
                .get(URI.concat("/").concat("1"));

        when(clienteService.obterClientePorId(1L)).thenThrow(ClienteNaoEncontradoException.class);

        mockMvc.perform(mockHttpServletRequest)
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
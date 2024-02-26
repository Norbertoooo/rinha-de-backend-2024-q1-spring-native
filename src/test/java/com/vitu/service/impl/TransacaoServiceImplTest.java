package com.vitu.service.impl;

import com.vitu.domain.Cliente;
import com.vitu.dto.request.TransacaoRequestDto;
import com.vitu.dto.response.CriarNovaTransacaoResponseDto;
import com.vitu.exception.SaldoInconsistenteException;
import com.vitu.factory.ClienteFactory;
import com.vitu.factory.TransacaoRequestFactory;
import com.vitu.repository.TransacaoRepository;
import com.vitu.service.ClienteService;
import com.vitu.service.TransacaoService;
import com.vitu.strategy.TransacaoStrategy;
import com.vitu.strategy.impl.CreditoStrategyImpl;
import com.vitu.strategy.impl.DebitoStrategyImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TransacaoServiceImplTest {

    @MockBean
    TransacaoRepository transacaoRepository;

    @MockBean
    ClienteService clienteService;

    @MockBean
    DebitoStrategyImpl debitoStrategy;

    @MockBean
    CreditoStrategyImpl creditoStrategy;

    TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
        Map<String, TransacaoStrategy> strategyMap = Map.of("c", creditoStrategy, "d", debitoStrategy);
        transacaoService = new TransacaoServiceImpl(transacaoRepository, clienteService, strategyMap);
    }

    @Test
    void deveEfetuarTransacaoDeDebito() {

        TransacaoRequestDto transacaoRequestDto = TransacaoRequestFactory.construirTransacaoRequestDeDebitoValida();

        Cliente cliente =  ClienteFactory.construirCliente();

        when(clienteService.obterClientePorId(1L)).thenReturn(cliente);

        when(debitoStrategy.efetuarTransacao(transacaoRequestDto, cliente)).thenCallRealMethod();

        CriarNovaTransacaoResponseDto response = transacaoService.efetuarTransacao(1L, transacaoRequestDto);

        Assertions.assertEquals(response.getLimite(), 1000L);
        Assertions.assertEquals(response.getSaldo(), 999L);

        verify(debitoStrategy,times(1)).efetuarTransacao(any(), any());
        verify(creditoStrategy,never()).efetuarTransacao(any(), any());
        verify(clienteService,times(1)).salvar(any());
        verify(transacaoRepository,times(1)).save(any());
    }

    @Test
    void deveEfetuarTransacaoDeCredito() {

        TransacaoRequestDto transacaoRequestDto = TransacaoRequestFactory.construirTransacaoRequestDeCreditoValida();

        Cliente cliente = ClienteFactory.construirCliente();

        when(clienteService.obterClientePorId(1L)).thenReturn(cliente);

        when(creditoStrategy.efetuarTransacao(transacaoRequestDto, cliente)).thenCallRealMethod();

        CriarNovaTransacaoResponseDto response = transacaoService.efetuarTransacao(1L, transacaoRequestDto);

        Assertions.assertEquals(response.getLimite(), 1000L);
        Assertions.assertEquals(response.getSaldo(), 1001L);

        verify(creditoStrategy,times(1)).efetuarTransacao(any(), any());
        verify(debitoStrategy,never()).efetuarTransacao(any(), any());
        verify(clienteService,times(1)).salvar(any());
        verify(transacaoRepository,times(1)).save(any());
    }

    @Test
    void deveRetornarSaldoInconsistenteQuandoEfetuarTransacaoDeDebitoInvalida() {

        TransacaoRequestDto transacaoRequestDto = TransacaoRequestFactory.construirTransacaoRequestDeDebitoValida();
        transacaoRequestDto.setValor(BigDecimal.valueOf(501));

        Cliente cliente = ClienteFactory.construirCliente(0L, 500L);

        when(clienteService.obterClientePorId(1L)).thenReturn(cliente);

        when(debitoStrategy.efetuarTransacao(transacaoRequestDto, cliente)).thenCallRealMethod();

        Assertions.assertThrows(SaldoInconsistenteException.class, ()-> transacaoService.efetuarTransacao(1L, transacaoRequestDto));

        verify(debitoStrategy,times(1)).efetuarTransacao(any(), any());
        verify(creditoStrategy,never()).efetuarTransacao(any(), any());
        verify(clienteService,never()).salvar(any());
        verify(transacaoRepository,never()).save(any());
    }

}
package com.vitu.strategy.impl;

import com.vitu.domain.Cliente;
import com.vitu.dto.request.TransacaoRequestDto;
import com.vitu.strategy.TransacaoStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("c")
@RequiredArgsConstructor
public class CreditoStrategyImpl implements TransacaoStrategy {

    @Override
    public Cliente efetuarTransacao(TransacaoRequestDto transacaoRequestDto, Cliente cliente) {
        log.info("Efetuando operação de crédito: {} - {}", transacaoRequestDto, cliente);

        cliente.setSaldo(cliente.getSaldo() + transacaoRequestDto.getValor().longValue());

        return cliente;
    }

}

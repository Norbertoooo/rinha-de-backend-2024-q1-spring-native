package com.vitu.strategy.impl;

import com.vitu.domain.Cliente;
import com.vitu.dto.request.TransacaoRequestDto;
import com.vitu.strategy.TransacaoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("c")
@RequiredArgsConstructor
public class CreditoStrategyImpl implements TransacaoStrategy {

    @Override
    public Cliente efetuarTransacao(TransacaoRequestDto transacaoRequestDto, Cliente cliente) {

        cliente.setSaldo(cliente.getSaldo() + transacaoRequestDto.getValor().longValue());

        return cliente;
    }

}

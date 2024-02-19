package com.vitu.strategy.impl;

import com.vitu.domain.Cliente;
import com.vitu.dto.request.TransacaoRequestDto;
import com.vitu.exception.SaldoInconsistenteException;
import com.vitu.strategy.TransacaoStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("d")
@RequiredArgsConstructor
public class DebitoStrategyImpl implements TransacaoStrategy {

    @Override
    public Cliente efetuarTransacao(TransacaoRequestDto transacaoRequestDto, Cliente cliente) {
        log.info("Efetuando operação de débito: {} - {}", transacaoRequestDto, cliente);

        if ((cliente.getSaldo() - transacaoRequestDto.getValor().longValue()) < (cliente.getLimite() * -1)) {
            throw new SaldoInconsistenteException();
        }

        cliente.setSaldo(cliente.getSaldo() - transacaoRequestDto.getValor().longValue());

        return cliente;
    }

}

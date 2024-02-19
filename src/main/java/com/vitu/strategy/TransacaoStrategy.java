package com.vitu.strategy;

import com.vitu.domain.Cliente;
import com.vitu.dto.request.TransacaoRequestDto;

public interface TransacaoStrategy {

    Cliente efetuarTransacao(TransacaoRequestDto transacaoRequestDto, Cliente cliente);

}

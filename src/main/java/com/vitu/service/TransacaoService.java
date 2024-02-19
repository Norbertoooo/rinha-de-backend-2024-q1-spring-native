package com.vitu.service;

import com.vitu.domain.Cliente;
import com.vitu.domain.Transacao;
import com.vitu.dto.request.TransacaoRequestDto;
import com.vitu.dto.response.CriarNovaTransacaoResponseDto;

import java.util.List;

public interface TransacaoService {

    CriarNovaTransacaoResponseDto efetuarTransacao(Long clienteId, TransacaoRequestDto transacaoRequestDto);

    List<Transacao> obterTransacoesPorDataDesc(Long clienteId);

    Transacao salvar(TransacaoRequestDto transacaoRequestDto, Cliente cliente);
}

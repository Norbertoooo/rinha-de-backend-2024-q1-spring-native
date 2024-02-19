package com.vitu.service.impl;

import com.vitu.domain.Cliente;
import com.vitu.domain.Transacao;
import com.vitu.dto.response.ExtratoResponseDto;
import com.vitu.dto.response.SaldoResponseDto;
import com.vitu.dto.response.TransacaoResponseDto;
import com.vitu.service.ClienteService;
import com.vitu.service.ExtratoService;
import com.vitu.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExtratoServiceImpl implements ExtratoService {

    private final TransacaoService transacaoService;
    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    @Override
    public ExtratoResponseDto obterExtratoPorClienteId(Long id) {

        log.info("Obtendo extrato para o cliente: {}", id);
        Cliente cliente = clienteService.obterClientePorId(id);

        List<Transacao> transacoes = transacaoService.obterTransacoesPorDataDesc(id);

        SaldoResponseDto saldoResponseDto = this.criarSaldoDto(cliente);

        return ExtratoResponseDto.builder()
                .saldo(saldoResponseDto)
                .ultimasTransacoes(this.criarTransacoesDto(transacoes))
                .build();
    }

    private List<TransacaoResponseDto> criarTransacoesDto(List<Transacao> transacoes) {

        return transacoes.parallelStream()
                .map(transacao -> modelMapper.map(transacao, TransacaoResponseDto.class))
                .toList();
    }

    private SaldoResponseDto criarSaldoDto(Cliente cliente) {
        return SaldoResponseDto.builder()
                .dataExtrato(ZonedDateTime.now())
                .total(cliente.getSaldo())
                .limite(cliente.getLimite())
                .build();
    }

}

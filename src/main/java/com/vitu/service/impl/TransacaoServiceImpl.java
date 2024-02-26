package com.vitu.service.impl;

import com.vitu.domain.Cliente;
import com.vitu.domain.TipoTransacao;
import com.vitu.domain.Transacao;
import com.vitu.dto.request.TransacaoRequestDto;
import com.vitu.dto.response.CriarNovaTransacaoResponseDto;
import com.vitu.exception.OperacaoNaoSuportadaException;
import com.vitu.repository.TransacaoRepository;
import com.vitu.service.ClienteService;
import com.vitu.service.TransacaoService;
import com.vitu.strategy.TransacaoStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ClienteService clienteService;
    private final Map<String, TransacaoStrategy> transacaoStrategyMap;

    @Override
    @Transactional
    public CriarNovaTransacaoResponseDto efetuarTransacao(Long clienteId, TransacaoRequestDto transacaoRequestDto) {

        log.info("Iniciando nova transação: {} - clienteId: {}", transacaoRequestDto, clienteId);

        Cliente cliente = clienteService.obterClientePorId(clienteId);

        TransacaoStrategy transacaoStrategy = this.getTransacaoStrategy(transacaoRequestDto.getTipo());

        Cliente clienteAtualizado = transacaoStrategy.efetuarTransacao(transacaoRequestDto, cliente);

        this.salvar(transacaoRequestDto, cliente);

        this.clienteService.salvar(clienteAtualizado);

        return CriarNovaTransacaoResponseDto.builder()
                .saldo(clienteAtualizado.getSaldo())
                .limite(clienteAtualizado.getLimite())
                .build();
    }

    private TransacaoStrategy getTransacaoStrategy(Character tipo) {
        return transacaoStrategyMap.entrySet()
                .stream()
                .filter(entry-> entry.getKey().equals(tipo.toString().toLowerCase()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(OperacaoNaoSuportadaException::new);
    }

    @Override
    public List<Transacao> obterTransacoesPorDataDesc(Long clienteId) {

        log.info("Obtendo transações para o cliente: {}", clienteId);

        clienteService.verificarSeExisteClientePorId(clienteId);

        return transacaoRepository.findTop10AllByCliente_IdOrderByRealizadaEmDesc(clienteId);
    }

    @Override
    public Transacao salvar(TransacaoRequestDto transacaoRequestDto, Cliente cliente) {

        Transacao transacao = Transacao.builder()
                .valor(transacaoRequestDto.getValor().longValue())
                .tipo(TipoTransacao.getTipo(transacaoRequestDto.getTipo()))
                .descricao(transacaoRequestDto.getDescricao())
                .realizadaEm(ZonedDateTime.now())
                .cliente(cliente)
                .build();

        log.info("Salvando nova transação: {}", transacao);

        return transacaoRepository.save(transacao);
    }
}

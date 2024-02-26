package com.vitu.service.impl;

import com.vitu.domain.Cliente;
import com.vitu.exception.ClienteNaoEncontradoException;
import com.vitu.repository.ClienteRepository;
import com.vitu.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente obterClientePorId(Long id) {
        log.info("Procurando cliente pelo id: {}", id);
        return clienteRepository.findByIdWithPessimistLock(id).orElseThrow(ClienteNaoEncontradoException::new);
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        log.info("Salvando cliente: {}", cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public void verificarSeExisteClientePorId(Long id) {
        log.info("Verificando se existe cliente pelo id: {}", id);
        if (Boolean.FALSE.equals(clienteRepository.existsById(id))) {
            throw new ClienteNaoEncontradoException();
        }

    }

}

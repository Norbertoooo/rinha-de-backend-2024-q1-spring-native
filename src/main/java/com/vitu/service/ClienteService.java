package com.vitu.service;

import com.vitu.domain.Cliente;

public interface ClienteService {

    Cliente obterClientePorId(Long id);

    Cliente salvar(Cliente cliente);

    void verificarSeExisteClientePorId(Long id);

}

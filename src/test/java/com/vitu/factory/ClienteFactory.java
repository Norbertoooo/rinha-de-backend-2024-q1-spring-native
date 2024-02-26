package com.vitu.factory;

import com.vitu.domain.Cliente;

public abstract class ClienteFactory {

    public static Cliente construirCliente() {
        return Cliente.builder()
                .saldo(1000L)
                .id(1L)
                .limite(1000L)
                .build();
    }

    public static Cliente construirCliente(Long saldo, Long limite) {
        return Cliente.builder()
                .saldo(saldo)
                .id(1L)
                .limite(limite)
                .build();
    }
}

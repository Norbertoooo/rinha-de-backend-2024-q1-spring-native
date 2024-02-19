package com.vitu.domain;

import com.fasterxml.jackson.annotation.JsonValue;


public enum TipoTransacao {
    C("crédito", 'c'),
    D("débito", 'd');

    private final String descricao;

    private final Character valor;

    TipoTransacao(String descricao, char valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public static TipoTransacao getTipo(Character character) {
        if (C.valor.equals(character)) {
            return C;
        }
        return D;
    }

    @JsonValue
    public Character getValor() {
        return this.valor;
    }

}

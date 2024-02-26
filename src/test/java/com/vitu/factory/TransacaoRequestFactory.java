package com.vitu.factory;

import com.vitu.domain.TipoTransacao;
import com.vitu.dto.request.TransacaoRequestDto;

import java.math.BigDecimal;

public abstract class TransacaoRequestFactory {

    public static final String DESCRICAO_VALIDA = "0123456789";
    public static final String DESCRICAO_INVALIDA = "01234567891";

    public static TransacaoRequestDto construirTransacaoRequestComValorNegatico() {
        return TransacaoRequestDto.builder()
                .valor(BigDecimal.valueOf(-1))
                .tipo(TipoTransacao.C.getValor())
                .descricao(DESCRICAO_VALIDA)
                .build();
    }

    public static TransacaoRequestDto construirTransacaoRequestComTipoInvalido() {
        return TransacaoRequestDto.builder()
                .valor(BigDecimal.valueOf(1))
                .tipo('s')
                .descricao(DESCRICAO_VALIDA)
                .build();
    }

    public static TransacaoRequestDto construirTransacaoRequestComDescricaoinvalida() {
        return TransacaoRequestDto.builder()
                .valor(BigDecimal.valueOf(1))
                .tipo(TipoTransacao.C.getValor())
                .descricao(DESCRICAO_INVALIDA)
                .build();
    }

    public static TransacaoRequestDto construirTransacaoRequestComDescricaoVazia() {
        return TransacaoRequestDto.builder()
                .valor(BigDecimal.valueOf(1))
                .tipo(TipoTransacao.C.getValor())
                .descricao("")
                .build();
    }

    public static TransacaoRequestDto construirTransacaoRequestDeDebitoValida() {
        return TransacaoRequestDto.builder()
                .valor(BigDecimal.valueOf(1))
                .tipo(TipoTransacao.D.getValor())
                .descricao(DESCRICAO_VALIDA)
                .build();
    }

    public static TransacaoRequestDto construirTransacaoRequestDeCreditoValida() {
        return TransacaoRequestDto.builder()
                .valor(BigDecimal.valueOf(1))
                .tipo(TipoTransacao.C.getValor())
                .descricao(DESCRICAO_VALIDA)
                .build();
    }
}

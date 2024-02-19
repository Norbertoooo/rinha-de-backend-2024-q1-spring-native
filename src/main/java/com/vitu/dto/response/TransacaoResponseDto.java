package com.vitu.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vitu.domain.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoResponseDto {

    private Long valor;

    private TipoTransacao tipo;

    private String descricao;

    @JsonProperty("realizada_em")
    private ZonedDateTime realizadaEm;

}

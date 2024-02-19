package com.vitu.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoResponseDto {

    private SaldoResponseDto saldo;

    @JsonProperty("ultimas_transacoes")
    private List<TransacaoResponseDto> ultimasTransacoes;

}

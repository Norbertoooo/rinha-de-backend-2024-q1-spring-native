package com.vitu.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriarNovaTransacaoResponseDto {

    private Long limite;
    private Long saldo;

}

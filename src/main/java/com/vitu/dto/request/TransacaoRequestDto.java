package com.vitu.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoRequestDto {

    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 0, message = "deve ser um numero inteiro")
    private BigDecimal valor;

    @NotNull
    private Character tipo;

    @Size(min = 1, max = 10)
    @NotNull
    private String descricao;

}

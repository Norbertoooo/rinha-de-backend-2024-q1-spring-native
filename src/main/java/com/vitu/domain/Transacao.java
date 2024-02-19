package com.vitu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long valor;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    private String descricao;

    private ZonedDateTime realizadaEm;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;


}

package com.vitu.repository;

import com.vitu.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findTop10AllByCliente_IdOrderByRealizadaEmDesc(Long id);
}

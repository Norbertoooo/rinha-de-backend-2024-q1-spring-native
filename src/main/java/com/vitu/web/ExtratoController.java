package com.vitu.web;

import com.vitu.service.ExtratoService;
import com.vitu.dto.response.ExtratoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor

public class ExtratoController {

    private final ExtratoService extratoService;

    @GetMapping("/clientes/{id}/extrato")
    public ResponseEntity<ExtratoResponseDto> obterExtrato(@PathVariable Long id) {

        log.info("Recebendo requisição para obter extrato do cliente: {}", id);
        ExtratoResponseDto extratoResponseDto = extratoService.obterExtratoPorClienteId(id);

        return ResponseEntity.ok(extratoResponseDto);
    }
}

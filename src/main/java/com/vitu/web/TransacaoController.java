package com.vitu.web;

import com.vitu.service.TransacaoService;
import com.vitu.dto.request.TransacaoRequestDto;
import com.vitu.dto.response.CriarNovaTransacaoResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping("/clientes/{id}/transacoes")
    public ResponseEntity<CriarNovaTransacaoResponseDto> criarNovaTransacao(@PathVariable Long id,
                                                                            @RequestBody @Valid TransacaoRequestDto transacaoRequestDto) {
        log.info("Recendo requisição para criação de nova transação: {}", transacaoRequestDto);
        CriarNovaTransacaoResponseDto criarNovaTransacaoResponseDto = transacaoService.efetuarTransacao(id, transacaoRequestDto);
        return ResponseEntity.ok(criarNovaTransacaoResponseDto);
    }

}

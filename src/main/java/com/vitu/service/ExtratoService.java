package com.vitu.service;


import com.vitu.dto.response.ExtratoResponseDto;

public interface ExtratoService {

    ExtratoResponseDto obterExtratoPorClienteId(Long id);
}

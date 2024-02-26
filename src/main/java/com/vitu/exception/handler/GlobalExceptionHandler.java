package com.vitu.exception.handler;

import com.vitu.exception.ClienteNaoEncontradoException;
import com.vitu.exception.OperacaoNaoSuportadaException;
import com.vitu.exception.SaldoInconsistenteException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<?> clienteNaoEncontradoHandler(HttpServletRequest request, ClienteNaoEncontradoException exception) {
        log.error(request.getMethod() + request.getRequestURI());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SaldoInconsistenteException.class)
    public ResponseEntity<?> saldoInconsistenteHandler(HttpServletRequest request, SaldoInconsistenteException exception) {
        log.error(request.getMethod() + request.getRequestURI());
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        log.error(request.getMethod() + request.getRequestURI());
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, Exception exception) {
        log.error(request.getRequestURI());
        log.error(exception.getMessage());
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(OperacaoNaoSuportadaException.class)
    public ResponseEntity<?> operacaoNaoSuportadaExceptionHandler(HttpServletRequest request, OperacaoNaoSuportadaException exception) {
        log.error(request.getMethod() + request.getRequestURI());
        return ResponseEntity.unprocessableEntity().build();
    }


}

package com.locatech.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata o erro 404 (Recurso Não Encontrado)
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<StandardError> entityNotFound(RecursoNaoEncontradoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND; // [cite: 16]
        StandardError err = new StandardError(Instant.now(), status.value(), "Recurso não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    // Trata o erro 400 (Regras de Negócio e validações manuais)
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<StandardError> businessRule(RegraNegocioException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // [cite: 16]
        StandardError err = new StandardError(Instant.now(), status.value(), "Regra de negócio violada", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    // Trata o erro 400 do Bean Validation (@Valid das entidades)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // [cite: 16]

        // Junta todas as mensagens de erro de validação dos campos numa única String
        String mensagens = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        StandardError err = new StandardError(Instant.now(), status.value(), "Erro de validação de dados", mensagens, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    // Trata erros inesperados do servidor (Erro 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> globalError(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // [cite: 17]
        StandardError err = new StandardError(Instant.now(), status.value(), "Erro interno do servidor", "Ocorreu um erro inesperado na aplicação.", request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
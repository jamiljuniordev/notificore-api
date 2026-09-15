package com.notificore.api.exception;

import com.notificore.api.dto.ErroRespostaDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroRespostaDTO> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDTO> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String mensagemErro = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((first, second) -> first + "; " + second)
                .orElse("Dados de requisição inválidos");

        ErroRespostaDTO erro = new ErroRespostaDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação",
                mensagemErro,
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
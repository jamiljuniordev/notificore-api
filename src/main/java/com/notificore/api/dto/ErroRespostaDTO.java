package com.notificore.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record ErroRespostaDTO(
        @Schema(description = "Código de status HTTP", example = "404")
        Integer status,

        @Schema(description = "Título do erro", example = "Recurso não encontrado")
        String error,

        @Schema(description = "Mensagem detalhada do erro", example = "Notificação não encontrada com o ID: 99")
        String message,

        @Schema(description = "Caminho da requisição", example = "/api/notificacoes/99")
        String path,

        @Schema(description = "Data e hora da ocorrência", example = "2026-09-15T10:56:53")
        LocalDateTime timestamp
) {}
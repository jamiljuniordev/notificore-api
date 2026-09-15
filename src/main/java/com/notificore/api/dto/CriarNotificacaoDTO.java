package com.notificore.api.dto;

import com.notificore.api.enums.CanalNotificacao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarNotificacaoDTO(
        @Schema(description = "ID do usuário destinatário", example = "1001")
        @NotNull(message = "O ID do usuário é obrigatório")
        Long usuarioId,

        @Schema(description = "Canal de envio da notificação", example = "EMAIL")
        @NotNull(message = "O canal é obrigatório")
        CanalNotificacao canal,

        @Schema(description = "Destino da mensagem (e-mail ou telefone)", example = "usuario@email.com")
        @NotBlank(message = "O destino é obrigatório")
        String destino,

        @Schema(description = "Conteúdo da mensagem a ser enviada", example = "Sua fatura do mês está disponível.")
        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem
) {}

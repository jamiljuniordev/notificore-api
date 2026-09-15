package com.notificore.api.dto;

import com.notificore.api.enums.CanalNotificacao;
import com.notificore.api.enums.StatusNotificacao;
import com.notificore.api.model.Notificacao;

import java.time.LocalDateTime;

public record NotificacaoResponseDTO(
        Long id,
        Long usuarioId,
        CanalNotificacao canal,
        String destino,
        String mensagem,
        StatusNotificacao status,
        LocalDateTime dataEnvio
) {
    // Construtor utilitário que aceita o objeto Notificacao diretamente
    public NotificacaoResponseDTO(Notificacao n) {
        this(
                n.getId(),
                n.getUsuarioId(),
                n.getCanal(),
                n.getDestino(),
                n.getMensagem(),
                n.getStatus(),
                n.getDataEnvio()
        );
    }
}
package com.notificore.api.service;

import com.notificore.api.dto.CriarNotificacaoDTO;
import com.notificore.api.dto.NotificacaoResponseDTO;
import com.notificore.api.enums.StatusNotificacao;
import com.notificore.api.model.Notificacao;
import com.notificore.api.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;

    public NotificacaoResponseDTO enviarNotificacao(CriarNotificacaoDTO dto) {
        Notificacao notificacao = new Notificacao();
        notificacao.setUsuarioId(dto.usuarioId());
        notificacao.setCanal(dto.canal());
        notificacao.setDestino(dto.destino());
        notificacao.setMensagem(dto.mensagem());
        notificacao.setStatus(StatusNotificacao.ENVIADO);
        notificacao.setDataEnvio(LocalDateTime.now());

        notificacao = repository.save(notificacao);

        return new NotificacaoResponseDTO(
                notificacao.getId(),
                notificacao.getUsuarioId(),
                notificacao.getCanal(),
                notificacao.getDestino(),
                notificacao.getMensagem(),
                notificacao.getStatus(),
                notificacao.getDataEnvio()
        );
    }

    public Page<NotificacaoResponseDTO> listarTodas(Pageable pageable) {
        return repository.findAll(pageable)
                .map(n -> new NotificacaoResponseDTO(
                        n.getId(), n.getUsuarioId(), n.getCanal(),
                        n.getDestino(), n.getMensagem(), n.getStatus(), n.getDataEnvio()));
    }

    public NotificacaoResponseDTO buscarPorId(Long id) {
        Notificacao n = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada com o ID: " + id));
        return new NotificacaoResponseDTO(
                n.getId(), n.getUsuarioId(), n.getCanal(),
                n.getDestino(), n.getMensagem(), n.getStatus(), n.getDataEnvio());
    }
}
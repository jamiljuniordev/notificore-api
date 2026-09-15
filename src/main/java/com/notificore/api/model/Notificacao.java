package com.notificore.api.model;

import com.notificore.api.enums.CanalNotificacao;
import com.notificore.api.enums.StatusNotificacao;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notificacoes")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CanalNotificacao canal;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false, length = 500)
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusNotificacao status;

    private LocalDateTime dataEnvio;

    // Construtor padrão exigido pela JPA
    public Notificacao() {}

    public Notificacao(Long usuarioId, CanalNotificacao canal, String destino, String mensagem) {
        this.usuarioId = usuarioId;
        this.canal = canal;
        this.destino = destino;
        this.mensagem = mensagem;
        this.status = StatusNotificacao.PENDENTE;
        this.dataEnvio = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public CanalNotificacao getCanal() {
        return canal;
    }

    public void setCanal(CanalNotificacao canal) {
        this.canal = canal;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public StatusNotificacao getStatus() {
        return status;
    }

    public void setStatus(StatusNotificacao status) {
        this.status = status;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}

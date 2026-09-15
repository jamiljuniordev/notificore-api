package com.notificore.api.repository;

import com.notificore.api.enums.StatusNotificacao;
import com.notificore.api.model.Notificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    // Busca paginada por status (ex: listar apenas FALHA ou ENVIADO)
    Page<Notificacao> findByStatus(StatusNotificacao status, Pageable pageable);

    // Busca paginada por ID de usuário
    Page<Notificacao> findByUsuarioId(Long usuarioId, Pageable pageable);
}

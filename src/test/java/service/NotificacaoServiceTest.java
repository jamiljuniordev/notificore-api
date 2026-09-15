package com.notificore.api.service;

import com.notificore.api.dto.CriarNotificacaoDTO;
import com.notificore.api.dto.NotificacaoResponseDTO;
import com.notificore.api.enums.CanalNotificacao;
import com.notificore.api.enums.StatusNotificacao;
import com.notificore.api.model.Notificacao;
import com.notificore.api.repository.NotificacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacaoServiceTest {

    @Mock
    private NotificacaoRepository repository;

    @InjectMocks
    private NotificacaoService service;

    @Test
    @DisplayName("Deve criar e enviar notificação com sucesso")
    void deveCriarNotificacaoComSucesso() {
        CriarNotificacaoDTO dto = new CriarNotificacaoDTO(
                1001L, CanalNotificacao.EMAIL, "usuario@email.com", "Sua fatura está pronta."
        );

        Notificacao notificacaoSalva = new Notificacao();
        notificacaoSalva.setId(1L);
        notificacaoSalva.setUsuarioId(dto.usuarioId());
        notificacaoSalva.setCanal(dto.canal());
        notificacaoSalva.setDestino(dto.destino());
        notificacaoSalva.setMensagem(dto.mensagem());
        notificacaoSalva.setStatus(StatusNotificacao.ENVIADO);
        notificacaoSalva.setDataEnvio(LocalDateTime.now());

        when(repository.save(any(Notificacao.class))).thenReturn(notificacaoSalva);

        NotificacaoResponseDTO resposta = service.enviarNotificacao(dto);

        assertNotNull(resposta);
        assertEquals(1L, resposta.id());
        assertEquals("usuario@email.com", resposta.destino());
        assertEquals(StatusNotificacao.ENVIADO, resposta.status());
        verify(repository, times(1)).save(any(Notificacao.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar notificação por ID inexistente")
    void deveLancarExcecaoQuandoIdNaoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));

        assertTrue(exception.getMessage().contains("Notificação não encontrada"));
        verify(repository, times(1)).findById(99L);
    }
}
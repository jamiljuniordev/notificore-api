package com.notificore.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificore.api.dto.CriarNotificacaoDTO;
import com.notificore.api.dto.NotificacaoResponseDTO;
import com.notificore.api.enums.CanalNotificacao;
import com.notificore.api.enums.StatusNotificacao;
import com.notificore.api.service.NotificacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificacaoController.class)
class NotificacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacaoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar status 201 Created ao enviar notificação")
    void deveRetornar201AoEnviarNotificacao() throws Exception {
        CriarNotificacaoDTO dto = new CriarNotificacaoDTO(
                1001L, CanalNotificacao.EMAIL, "usuario@email.com", "Sua fatura está disponível."
        );

        NotificacaoResponseDTO responseDTO = new NotificacaoResponseDTO(
                1L, 1001L, CanalNotificacao.EMAIL, "usuario@email.com",
                "Sua fatura está disponível.", StatusNotificacao.ENVIADO, LocalDateTime.now()
        );

        when(service.enviarNotificacao(any(CriarNotificacaoDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/notificacoes/enviar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("ENVIADO"));
    }

    @Test
    @DisplayName("Deve retornar 404 Not Found quando buscar notificação por ID inexistente")
    void deveRetornar404QuandoIdNaoEncontrado() throws Exception {
        Long idInexistente = 99L;

        when(service.buscarPorId(idInexistente))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Notificação não encontrada"));

        mockMvc.perform(get("/api/notificacoes/{id}", idInexistente)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
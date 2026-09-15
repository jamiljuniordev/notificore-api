package com.notificore.api.controller;

import com.notificore.api.dto.CriarNotificacaoDTO;
import com.notificore.api.dto.NotificacaoResponseDTO;
import com.notificore.api.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificacoes")
@Tag(name = "Notificações", description = "Endpoints para criação e consulta de notificações")
public class NotificacaoController {

    @Autowired
    private NotificacaoService service;

    @Operation(summary = "Enviar uma nova notificação", description = "Registra e processa o envio de uma notificação por E-mail, PUSH ou SMS.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificação criada e enviada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos ou campos obrigatórios ausentes")
    })
    @PostMapping("/enviar")
    public ResponseEntity<NotificacaoResponseDTO> enviar(@Valid @RequestBody CriarNotificacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.enviarNotificacao(dto));
    }

    @Operation(summary = "Listar notificações", description = "Retorna uma lista paginada de todas as notificações registradas.")
    @ApiResponse(responseCode = "200", description = "Lista obtida com sucesso")
    @GetMapping
    public ResponseEntity<Page<NotificacaoResponseDTO>> listarTodas(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.listarTodas(pageable));
    }

    @Operation(summary = "Buscar notificação por ID", description = "Retorna os detalhes de uma notificação específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificação encontrada"),
            @ApiResponse(responseCode = "404", description = "Notificação não encontrada para o ID informado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
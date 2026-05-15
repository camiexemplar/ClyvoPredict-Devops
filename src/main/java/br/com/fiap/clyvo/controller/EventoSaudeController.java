package br.com.fiap.clyvo.controller;

import br.com.fiap.clyvo.dto.EventoSaudeRequestDTO;
import br.com.fiap.clyvo.dto.EventoSaudeResponseDTO;
import br.com.fiap.clyvo.service.EventoSaudeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
public class EventoSaudeController {

    @Autowired
    private EventoSaudeService service;

    @PostMapping
    public ResponseEntity<EventoSaudeResponseDTO> registrar(@Valid @RequestBody EventoSaudeRequestDTO dto) {
        // Envia os dados para o Service fazer a mágica do Health Score
        EventoSaudeResponseDTO response = service.registrar(dto);

        // Retorna 201 CREATED e os dados salvos
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
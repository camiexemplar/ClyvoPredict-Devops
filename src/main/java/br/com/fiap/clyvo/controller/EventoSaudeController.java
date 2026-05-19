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
@CrossOrigin(origins = "*") // Libera o acesso para o aplicativo mobile
public class EventoSaudeController {

    @Autowired
    private EventoSaudeService service;

    @PostMapping
    public ResponseEntity<EventoSaudeResponseDTO> cadastrarEvento(@Valid @RequestBody EventoSaudeRequestDTO dto) {
        // Agora a variável recebe o DTO corretamente retornado pelo Service
        EventoSaudeResponseDTO eventoSalvo = service.cadastrarEvento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoSalvo);
    }
}
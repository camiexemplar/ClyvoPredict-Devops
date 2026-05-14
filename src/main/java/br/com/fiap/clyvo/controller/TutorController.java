package br.com.fiap.clyvo.controller;

import br.com.fiap.clyvo.dto.TutorRequestDTO;
import br.com.fiap.clyvo.dto.TutorResponseDTO;
import br.com.fiap.clyvo.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutores")
public class TutorController {

    @Autowired
    private TutorService service;

    @PostMapping
    public ResponseEntity<TutorResponseDTO> cadastrar(@Valid @RequestBody TutorRequestDTO dto) {
        // O @Valid garante que aquelas regras (NotBlank, Email) que colocamos no DTO sejam checadas!
        TutorResponseDTO response = service.cadastrar(dto);

        // Retorna o status 201 CREATED (Padrão RESTful exigido no challenge)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

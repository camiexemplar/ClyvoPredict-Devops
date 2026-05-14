package br.com.fiap.clyvo.controller;

import br.com.fiap.clyvo.dto.PetRequestDTO;
import br.com.fiap.clyvo.dto.PetResponseDTO;
import br.com.fiap.clyvo.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService service;

    @PostMapping
    public ResponseEntity<PetResponseDTO> cadastrar(@Valid @RequestBody PetRequestDTO dto) {
        // Envia o DTO validado para o Service fazer a mágica
        PetResponseDTO response = service.cadastrar(dto);

        // Retorna o status 201 CREATED e os dados salvos
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
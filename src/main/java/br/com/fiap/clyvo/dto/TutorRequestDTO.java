package br.com.fiap.clyvo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TutorRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "O telefone é obrigatório para os alertas via WhatsApp")
        String telefone
) {
}
package br.com.fiap.clyvo.dto;

import br.com.fiap.clyvo.model.enums.TipoEvento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EventoSaudeRequestDTO(

        @NotBlank(message = "A descrição do evento é obrigatória (ex: Vacina V10)")
        String descricao,

        @NotNull(message = "O tipo do evento é obrigatório (ex: VACINA, VERMIFUGO)")
        TipoEvento tipo,

        @NotNull(message = "A data do evento é obrigatória")
        @PastOrPresent(message = "A data do evento não pode ser no futuro")
        LocalDate dataEvento,

        @NotNull(message = "O ID do pet é obrigatório para vincular o evento")
        Long petId
) {
}

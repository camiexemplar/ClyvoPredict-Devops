package br.com.fiap.clyvo.dto;

import br.com.fiap.clyvo.model.enums.TipoEvento;
import java.time.LocalDate;

public record EventoSaudeResponseDTO(
        Long id,
        String descricao,
        TipoEvento tipo,
        LocalDate dataEvento,
        Long petId
) {
}

package br.com.fiap.clyvo.service;

import br.com.fiap.clyvo.dto.EventoSaudeRequestDTO;
import br.com.fiap.clyvo.dto.EventoSaudeResponseDTO;
import br.com.fiap.clyvo.model.EventoSaude;
import br.com.fiap.clyvo.model.Pet;
import br.com.fiap.clyvo.model.enums.TipoEvento;
import br.com.fiap.clyvo.repository.EventoSaudeRepository;
import br.com.fiap.clyvo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoSaudeService {

    @Autowired
    private EventoSaudeRepository eventoRepository;

    @Autowired
    private PetRepository petRepository;

    @Transactional
    public EventoSaudeResponseDTO registrar(EventoSaudeRequestDTO dto) {
        // 1. Busca o Pet
        Pet pet = petRepository.findById(dto.petId())
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        // 2. Converte DTO para Entidade
        EventoSaude evento = new EventoSaude();
        evento.setDescricao(dto.descricao());
        evento.setTipo(dto.tipo());
        evento.setDataEvento(dto.dataEvento());
        evento.setPet(pet);

        // 3. Lógica do Health Score
        atualizarScore(pet, dto.tipo());

        // 4. Salva o evento e o pet (atualizado)
        evento = eventoRepository.save(evento);
        petRepository.save(pet);

        return new EventoSaudeResponseDTO(
                evento.getId(),
                evento.getDescricao(),
                evento.getTipo(),
                evento.getDataEvento(),
                pet.getId()
        );
    }

    private void atualizarScore(Pet pet, TipoEvento tipo) {
        int atual = pet.getHealthScore();
        int novoScore = atual;

        switch (tipo) {
            case VACINA -> novoScore += 10;
            case VERMIFUGO, CONSULTA_ROTINA -> novoScore += 5;
            case EXAME -> novoScore += 2;
            case CIRURGIA -> novoScore -= 15;
            case EMERGENCIA -> novoScore -= 20;
        }

        // Garante que o score fique entre 0 e 100
        pet.setHealthScore(Math.max(0, Math.min(100, novoScore)));
    }
}

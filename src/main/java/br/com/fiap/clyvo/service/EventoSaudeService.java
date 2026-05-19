package br.com.fiap.clyvo.service;

import br.com.fiap.clyvo.dto.EventoSaudeRequestDTO;
import br.com.fiap.clyvo.dto.EventoSaudeResponseDTO;
import br.com.fiap.clyvo.model.EventoSaude;
import br.com.fiap.clyvo.model.Pet;
import br.com.fiap.clyvo.repository.EventoSaudeRepository;
import br.com.fiap.clyvo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoSaudeService {

    @Autowired
    private EventoSaudeRepository repository;

    @Autowired
    private PetRepository petRepository;

    @Transactional
    public EventoSaudeResponseDTO cadastrarEvento(EventoSaudeRequestDTO dto) {
        // 1. Busca o Pet no banco de dados
        Pet pet = petRepository.findById(dto.petId())
                .orElseThrow(() -> new RuntimeException("Pet não encontrado com o ID: " + dto.petId()));

        // 2. Cria o novo evento de saúde
        EventoSaude evento = new EventoSaude();
        evento.setPet(pet);
        evento.setTipoEvento(dto.tipoEvento());
        evento.setDescricao(dto.descricao());
        evento.setDataEvento(dto.dataEvento());

        // 3. A Mágica do Health Score: Calcula o impacto
        int scoreAtual = pet.getHealthScore();
        int impacto = dto.tipoEvento().getImpactoScore();
        int novoScore = scoreAtual + impacto;

        // 4. Regras de limite (O score não pode ser maior que 100 nem menor que 0)
        if (novoScore > 100) {
            novoScore = 100;
        } else if (novoScore < 0) {
            novoScore = 0;
        }

        // 5. Atualiza o score do Pet e salva
        pet.setHealthScore(novoScore);
        petRepository.save(pet);

        // 6. Salva o evento criado
        evento = repository.save(evento);

        // 7. Retorna o DTO limpo e seguro com o novo score atualizado
        return new EventoSaudeResponseDTO(
                evento.getId(),
                pet.getId(),
                evento.getTipoEvento(),
                evento.getDescricao(),
                evento.getDataEvento(),
                pet.getHealthScore()
        );
    }
}
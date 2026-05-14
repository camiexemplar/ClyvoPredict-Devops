package br.com.fiap.clyvo.service;

import br.com.fiap.clyvo.dto.TutorRequestDTO;
import br.com.fiap.clyvo.dto.TutorResponseDTO;
import br.com.fiap.clyvo.model.Tutor;
import br.com.fiap.clyvo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public TutorResponseDTO cadastrar(TutorRequestDTO dto) {
        // 1. Converte o DTO (que veio da requisição) para a Entidade
        Tutor tutor = new Tutor();
        tutor.setNome(dto.nome());
        tutor.setEmail(dto.email());
        tutor.setTelefone(dto.telefone());

        // 2. Salva no banco de dados Oracle usando o Repository
        tutor = repository.save(tutor);

        // 3. Converte a Entidade salva de volta para um DTO de Resposta
        return new TutorResponseDTO(
                tutor.getId(),
                tutor.getNome(),
                tutor.getEmail(),
                tutor.getTelefone()
        );
    }
}
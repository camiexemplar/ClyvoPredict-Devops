package br.com.fiap.clyvo.service;

import br.com.fiap.clyvo.dto.TutorRequestDTO;
import br.com.fiap.clyvo.dto.TutorResponseDTO;
import br.com.fiap.clyvo.model.Tutor;
import br.com.fiap.clyvo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public TutorResponseDTO cadastrar(TutorRequestDTO dto) {
        // Converte o DTO para Entidade
        Tutor tutor = new Tutor();
        tutor.setNome(dto.nome());
        tutor.setEmail(dto.email());
        tutor.setTelefone(dto.telefone());

        // Salva no banco de dados Oracle
        tutor = repository.save(tutor);

        // Retorna o DTO de Resposta
        return new TutorResponseDTO(
                tutor.getId(),
                tutor.getNome(),
                tutor.getEmail(),
                tutor.getTelefone()
        );
    }

    public Page<TutorResponseDTO> listar(Pageable paginacao) {
        // Busca todos com paginação e converte para DTO
        return repository.findAll(paginacao)
                .map(tutor -> new TutorResponseDTO(
                        tutor.getId(),
                        tutor.getNome(),
                        tutor.getEmail(),
                        tutor.getTelefone()
                ));
    }
}
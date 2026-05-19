package br.com.fiap.clyvo.service;

import br.com.fiap.clyvo.dto.PetRequestDTO;
import br.com.fiap.clyvo.dto.PetResponseDTO;
import br.com.fiap.clyvo.model.Pet;
import br.com.fiap.clyvo.model.Tutor;
import br.com.fiap.clyvo.repository.PetRepository;
import br.com.fiap.clyvo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    @Autowired
    private TutorRepository tutorRepository;

    @Transactional
    public PetResponseDTO cadastrar(PetRequestDTO dto) {
        Tutor tutor = tutorRepository.findById(dto.tutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + dto.tutorId()));

        Pet pet = new Pet();
        pet.setNome(dto.nome());
        pet.setEspecie(dto.especie());
        pet.setRaca(dto.raca());
        pet.setIdade(dto.idade());
        pet.setPeso(dto.peso());
        pet.setHealthScore(100); // Todo pet inicia com o score máximo de saúde
        pet.setTutor(tutor);

        pet = repository.save(pet);
        return converterParaDTO(pet);
    }

    public Page<PetResponseDTO> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(this::converterParaDTO);
    }

    public PetResponseDTO buscarPorId(Long id) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado com o ID: " + id));
        return converterParaDTO(pet);
    }

    @Transactional
    public PetResponseDTO atualizar(Long id, PetRequestDTO dto) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado com o ID: " + id));

        Tutor tutor = tutorRepository.findById(dto.tutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + dto.tutorId()));

        pet.setNome(dto.nome());
        pet.setEspecie(dto.especie());
        pet.setRaca(dto.raca());
        pet.setIdade(dto.idade());
        pet.setPeso(dto.peso());
        pet.setTutor(tutor);
        // Importante: mantemos o healthScore que ele já tinha acumulado antes!

        pet = repository.save(pet);
        return converterParaDTO(pet);
    }

    @Transactional
    public void excluir(Long id) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado com o ID: " + id));
        repository.delete(pet);
    }

    private PetResponseDTO converterParaDTO(Pet pet) {
        return new PetResponseDTO(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getIdade(),
                pet.getPeso(),
                pet.getHealthScore(),
                pet.getTutor().getId()
        );
    }
}
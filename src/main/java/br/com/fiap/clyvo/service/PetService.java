package br.com.fiap.clyvo.service;

import br.com.fiap.clyvo.dto.PetRequestDTO;
import br.com.fiap.clyvo.dto.PetResponseDTO;
import br.com.fiap.clyvo.model.Pet;
import br.com.fiap.clyvo.model.Tutor;
import br.com.fiap.clyvo.repository.PetRepository;
import br.com.fiap.clyvo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public PetResponseDTO cadastrar(PetRequestDTO dto) {
        // 1. Verifica se o Tutor existe no banco de dados
        Tutor tutor = tutorRepository.findById(dto.tutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID informado."));

        // 2. Converte o DTO para a Entidade Pet
        Pet pet = new Pet();
        pet.setNome(dto.nome());
        pet.setEspecie(dto.especie());
        pet.setRaca(dto.raca());
        pet.setIdade(dto.idade());
        pet.setPeso(dto.peso());
        pet.setTutor(tutor);
        // Nota: O healthScore não precisa ser "setado" aqui porque já definimos que o padrão é 100 na Entidade!

        // 3. Salva no banco de dados Oracle
        pet = petRepository.save(pet);

        // 4. Retorna o DTO de Resposta
        return new PetResponseDTO(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getIdade(),
                pet.getPeso(),
                pet.getHealthScore(),
                tutor.getId()
        );
    }
}
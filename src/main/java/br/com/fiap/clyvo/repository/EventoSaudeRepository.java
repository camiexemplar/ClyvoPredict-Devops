package br.com.fiap.clyvo.repository;

import br.com.fiap.clyvo.model.EventoSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoSaudeRepository extends JpaRepository<EventoSaude, Long> {

    // Esse método mágico do Spring Data vai nos permitir buscar todo o histórico de um pet específico!
    List<EventoSaude> findByPetIdOrderByDataEventoDesc(Long petId);

}

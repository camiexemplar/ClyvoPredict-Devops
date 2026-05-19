package br.com.fiap.clyvo.repository;

import br.com.fiap.clyvo.model.EventoSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoSaudeRepository extends JpaRepository<EventoSaude, Long> {

    List<EventoSaude> findByPetIdOrderByDataEventoDesc(Long petId);

}

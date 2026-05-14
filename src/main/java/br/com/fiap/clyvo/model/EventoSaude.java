package br.com.fiap.clyvo.model;

import br.com.fiap.clyvo.model.enums.TipoEvento;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_evento_saude")
public class EventoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao; // Ex: "Vacina V10" ou "Check-up anual"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEvento tipo;

    @Column(nullable = false)
    private LocalDate dataEvento;

    // Relacionamento: Muitos Eventos de Saúde pertencem a Um Pet
    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    // --- Construtores, Getters e Setters ---

    public EventoSaude() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}

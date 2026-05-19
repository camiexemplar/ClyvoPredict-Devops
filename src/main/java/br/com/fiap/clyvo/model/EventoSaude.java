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

    // Relacionamento: Vários eventos pertencem a um Pet
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    // Salva o nome do Enum (ex: "VACINA", "ACIDENTE") no banco de dados
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEvento tipoEvento;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataEvento;

    // Construtor padrão (Obrigatório para o Spring/JPA)
    public EventoSaude() {
    }

    // Construtor completo
    public EventoSaude(Long id, Pet pet, TipoEvento tipoEvento, String descricao, LocalDate dataEvento) {
        this.id = id;
        this.pet = pet;
        this.tipoEvento = tipoEvento;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
    }

    // --- Getters e Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }
}
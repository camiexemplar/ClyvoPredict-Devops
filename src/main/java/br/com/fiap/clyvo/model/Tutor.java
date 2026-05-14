package br.com.fiap.clyvo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "TB_TUTOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do tutor é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "O telefone é obrigatório para os alertas do WhatsApp")
    @Column(nullable = false, length = 20)
    private String telefone;

    // Relacionamento: 1 Tutor possui Vários Pets
    // O 'mappedBy' indica que o mapeamento é feito pelo atributo 'tutor' na classe Pet
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;
}
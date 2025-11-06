package com.nadic.desafiobackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="tb_disciplinas",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"codigo"})
        })
@Data
@NoArgsConstructor
public class Disciplina {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String codigo;

    @ManyToOne
    @JoinColumn(name="curso_id")
    private Curso curso;

    @ManyToMany
    @JoinTable( name = "tb_professores_by_disciplina",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Aluno> alunos;
}

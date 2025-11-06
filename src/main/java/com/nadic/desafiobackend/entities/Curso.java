package com.nadic.desafiobackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_cursos",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"codigo"})
        })
@Data
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String codigo;

}

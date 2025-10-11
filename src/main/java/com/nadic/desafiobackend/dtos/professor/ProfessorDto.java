package com.nadic.desafiobackend.dtos.professor;

import com.nadic.desafiobackend.entities.Professor;
import org.springframework.beans.BeanUtils;

public class ProfessorDto {
    private Long id;
    private String nome;
    private int matricula;

    public ProfessorDto() {
    }
    public ProfessorDto(Professor professor) {
        BeanUtils.copyProperties(professor, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}

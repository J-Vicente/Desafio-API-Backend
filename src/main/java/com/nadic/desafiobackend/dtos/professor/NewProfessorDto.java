package com.nadic.desafiobackend.dtos.professor;

import com.nadic.desafiobackend.entities.Professor;
import org.springframework.beans.BeanUtils;

public class NewProfessorDto {
    private String nome;
    private Integer matricula;

    public NewProfessorDto() {
    }

    public NewProfessorDto(Professor professor) {
        BeanUtils.copyProperties(professor, this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
}

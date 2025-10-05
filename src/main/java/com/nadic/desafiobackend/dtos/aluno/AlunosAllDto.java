package com.nadic.desafiobackend.dtos.aluno;

import com.nadic.desafiobackend.entities.Aluno;
import org.springframework.beans.BeanUtils;

public class AlunosAllDto {
    private Long id;
    private String nome;
    private int matricula;
    private String curso;

    public AlunosAllDto() {
    }

    public AlunosAllDto(Aluno aluno) {
        BeanUtils.copyProperties(aluno, this);
        this.curso = aluno.getCurso().getNome();
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}

package com.nadic.desafiobackend.dtos.aluno;

import com.nadic.desafiobackend.entities.Aluno;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class NewAlunoDto {
    private String nome;
    private Integer matricula;
    private Long curso_id;
    private List<Long> disciplinas_id;

    public NewAlunoDto() {
    }

    public NewAlunoDto(Aluno aluno) {
        BeanUtils.copyProperties(aluno, this);
        this.curso_id = aluno.getCurso().getId();
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

    public Long getCurso() {
        return curso_id;
    }

    public void setCurso(Long curso) {
        this.curso_id = curso;
    }

    public List<Long> getDisciplinas_id() {
        return disciplinas_id;
    }

    public void setDisciplinas_id(List<Long> disciplinas_id) {
        this.disciplinas_id = disciplinas_id;
    }
}

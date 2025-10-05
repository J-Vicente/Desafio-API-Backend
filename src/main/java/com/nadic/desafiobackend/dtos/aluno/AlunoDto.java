package com.nadic.desafiobackend.dtos.aluno;

import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Disciplina;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class AlunoDto {
    private Long id;
    private String nome;
    private int matricula;
    private String curso;
    private List<String> disciplinas;

    public AlunoDto() {
    }

    public AlunoDto(Aluno aluno) {
        BeanUtils.copyProperties(aluno, this);
        this.curso = aluno.getCurso().getNome();
        this.disciplinas = aluno.getDisciplinas().stream().map(Disciplina::getNome).toList();
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

    public List<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }
}

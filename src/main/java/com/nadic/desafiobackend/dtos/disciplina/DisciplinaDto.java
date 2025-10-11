package com.nadic.desafiobackend.dtos.disciplina;

import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Disciplina;
import com.nadic.desafiobackend.entities.Professor;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class DisciplinaDto {
    private Long id;
    private String nome;
    private String codigo;
    private Long cursoId;
    private List<String> professores;
    private List<String> alunos;


    public DisciplinaDto(){
    }

    public DisciplinaDto(Disciplina disciplina) {
        BeanUtils.copyProperties(disciplina, this);
        this.cursoId =  disciplina.getCurso().getId();
        this.professores = disciplina.getProfessores().stream().map(Professor::getNome).toList();
        this.alunos = disciplina.getAlunos().stream().map(Aluno::getNome).toList();
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public List<String> getProfessores() {
        return professores;
    }

    public void setProfessores(List<String> professores) {
        this.professores = professores;
    }

    public List<String> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<String> alunos) {
        this.alunos = alunos;
    }
}

package com.nadic.desafiobackend.dtos.disciplina;

import com.nadic.desafiobackend.entities.Disciplina;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class NewDisciplinaDto {
    private Long id;
    private String nome;
    private String codigo;
    private Long cursoId;
    private List<Long> professores;
    private List<Long> alunos;


    public NewDisciplinaDto(){
    }

    public NewDisciplinaDto(Disciplina disciplina) {
        BeanUtils.copyProperties(disciplina, this);
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

    public List<Long> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Long> professores) {
        this.professores = professores;
    }

    public List<Long> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Long> alunos) {
        this.alunos = alunos;
    }
}

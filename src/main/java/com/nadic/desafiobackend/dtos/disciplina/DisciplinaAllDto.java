package com.nadic.desafiobackend.dtos.disciplina;

import com.nadic.desafiobackend.entities.Disciplina;
import org.springframework.beans.BeanUtils;

public class DisciplinaAllDto {
    private Long id;
    private String nome;
    private String codigo;
    private Long cursoId;

    public DisciplinaAllDto(){
    }

    public DisciplinaAllDto(Disciplina disciplina) {
        BeanUtils.copyProperties(disciplina, this);
        this.cursoId =  disciplina.getCurso().getId();
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
}

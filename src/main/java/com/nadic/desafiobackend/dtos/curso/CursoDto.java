package com.nadic.desafiobackend.dtos.curso;

import com.nadic.desafiobackend.entities.Curso;
import org.springframework.beans.BeanUtils;

public class CursoDto {
    private Long id;
    private String nome;
    private String codigo;

    public  CursoDto() {
    }
    public CursoDto(Curso curso) {
        BeanUtils.copyProperties(curso,this);
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
}

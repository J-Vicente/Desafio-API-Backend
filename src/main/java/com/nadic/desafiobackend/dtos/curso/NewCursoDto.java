package com.nadic.desafiobackend.dtos.curso;

import com.nadic.desafiobackend.entities.Curso;
import org.springframework.beans.BeanUtils;

public class NewCursoDto {
    private String nome;
    private String codigo;

    public NewCursoDto() {
    }

    public NewCursoDto(Curso curso) {
        BeanUtils.copyProperties(curso, this);
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

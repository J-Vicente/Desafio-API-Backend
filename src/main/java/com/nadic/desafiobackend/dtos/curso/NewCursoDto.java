package com.nadic.desafiobackend.dtos.curso;

import com.nadic.desafiobackend.entities.Curso;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class NewCursoDto {
    private String nome;
    private String codigo;


    public NewCursoDto(Curso curso) {
        BeanUtils.copyProperties(curso, this);
    }

}

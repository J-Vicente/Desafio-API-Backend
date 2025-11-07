package com.nadic.desafiobackend.dtos.disciplina;

import com.nadic.desafiobackend.entities.Disciplina;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class DisciplinaAllDto {
    private Long id;
    private String nome;
    private String codigo;
    private Long cursoId;


    public DisciplinaAllDto(Disciplina disciplina) {
        BeanUtils.copyProperties(disciplina, this);
        this.cursoId =  disciplina.getCurso().getId();
    }


}

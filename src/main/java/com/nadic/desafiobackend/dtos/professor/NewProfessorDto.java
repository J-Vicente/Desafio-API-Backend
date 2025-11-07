package com.nadic.desafiobackend.dtos.professor;

import com.nadic.desafiobackend.entities.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class NewProfessorDto {
    private String nome;
    private Integer matricula;

    public NewProfessorDto(Professor professor) {
        BeanUtils.copyProperties(professor, this);
    }


}

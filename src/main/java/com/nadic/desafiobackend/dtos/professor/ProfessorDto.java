package com.nadic.desafiobackend.dtos.professor;

import com.nadic.desafiobackend.entities.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class ProfessorDto {
    private Long id;
    private String nome;
    private int matricula;

}

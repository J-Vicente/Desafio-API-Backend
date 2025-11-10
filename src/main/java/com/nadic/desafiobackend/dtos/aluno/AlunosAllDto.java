package com.nadic.desafiobackend.dtos.aluno;

import com.nadic.desafiobackend.entities.Aluno;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class AlunosAllDto {
    private Long id;
    private String nome;
    private int matricula;
    private String curso;

}

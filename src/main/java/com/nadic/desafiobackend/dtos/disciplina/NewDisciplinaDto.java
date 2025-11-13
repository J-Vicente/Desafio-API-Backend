package com.nadic.desafiobackend.dtos.disciplina;

import com.nadic.desafiobackend.entities.Disciplina;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class NewDisciplinaDto {
    private String nome;
    private String codigo;
    private Long cursoId;
    private List<Long> professores;
    private List<Long> alunos;

}

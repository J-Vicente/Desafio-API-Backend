package com.nadic.desafiobackend.dtos.aluno;

import com.nadic.desafiobackend.entities.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewAlunoDto {
    private String nome;
    private Integer matricula;
    private Long cursoId;
    private List<Long> disciplinasId;

}

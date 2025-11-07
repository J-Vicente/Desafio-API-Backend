package com.nadic.desafiobackend.dtos.aluno;

import com.nadic.desafiobackend.entities.Aluno;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class NewAlunoDto {
    private String nome;
    private Integer matricula;
    private Long cursoId;
    private List<Long> disciplinasId;


    public NewAlunoDto(Aluno aluno) {
        BeanUtils.copyProperties(aluno, this);
        this.cursoId = aluno.getCurso().getId();
    }

}

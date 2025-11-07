package com.nadic.desafiobackend.dtos.aluno;

import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Disciplina;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class AlunoDto {
    private Long id;
    private String nome;
    private int matricula;
    private String curso;
    private List<String> disciplinas;


    public AlunoDto(Aluno aluno) {
        BeanUtils.copyProperties(aluno, this);
        this.curso = aluno.getCurso().getNome();
        this.disciplinas = aluno.getDisciplinas().stream().map(Disciplina::getNome).toList();
    }

}

package com.nadic.desafiobackend.dtos.disciplina;

import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Disciplina;
import com.nadic.desafiobackend.entities.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class DisciplinaDto {
    private Long id;
    private String nome;
    private String codigo;
    private Long cursoId;
    private List<String> professores;
    private List<String> alunos;


    public DisciplinaDto(Disciplina disciplina) {
        BeanUtils.copyProperties(disciplina, this);
        this.cursoId =  disciplina.getCurso().getId();
        this.professores = disciplina.getProfessores().stream().map(Professor::getNome).toList();
        this.alunos = disciplina.getAlunos().stream().map(Aluno::getNome).toList();
    }

}

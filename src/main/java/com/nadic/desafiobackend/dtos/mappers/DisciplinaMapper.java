package com.nadic.desafiobackend.dtos.mappers;

import com.nadic.desafiobackend.dtos.disciplina.DisciplinaAllDto;
import com.nadic.desafiobackend.dtos.disciplina.DisciplinaDto;
import com.nadic.desafiobackend.dtos.disciplina.NewDisciplinaDto;
import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Curso;
import com.nadic.desafiobackend.entities.Disciplina;
import com.nadic.desafiobackend.entities.Professor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DisciplinaMapper {

    @Mapping(target = "cursoId", source = "curso.id")
    DisciplinaAllDto toAllDto(Disciplina disciplina);

    List<DisciplinaAllDto> toDtoList(List<Disciplina> disciplinas);

    @Mapping(target = "cursoId", source = "curso.id")
    DisciplinaDto toDto(Disciplina disciplinas);

    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "professores", ignore = true)
    @Mapping(target = "alunos", ignore = true)
    Disciplina toEntity(NewDisciplinaDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(NewDisciplinaDto dto, @MappingTarget Disciplina disciplinas);


    default List<String> mapAlunosNome(List<Aluno> alunos) {
        return alunos != null ? alunos.stream().map(Aluno::getNome).toList() : null;
    }

    default List<String> mapProfessoresNome(List<Professor> professores) {
        return professores != null ? professores.stream().map(Professor::getNome).toList() : null;
    }

    default Professor mapProfessor(Long id) {
        if (id == null) return null;
        Professor professor = new Professor();
        professor.setId(id);
        return professor;
    }

    default List<Professor> mapProfessoresId(List<Long> professores) {
        if (professores == null) return null;
        return professores.stream().map(this::mapProfessor).toList();
    }

    default Aluno mapAluno(Long id) {
        if (id == null) return null;
        Aluno aluno = new Aluno();
        aluno.setId(id);
        return aluno;
    }

    default List<Aluno> mapAlunosId(List<Long> alunos) {
        if (alunos == null) return null;
        return alunos.stream().map(this::mapAluno).toList();
    }
}

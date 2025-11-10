package com.nadic.desafiobackend.dtos.mappers;

import com.nadic.desafiobackend.dtos.aluno.AlunoDto;
import com.nadic.desafiobackend.dtos.aluno.AlunosAllDto;
import com.nadic.desafiobackend.dtos.aluno.NewAlunoDto;
import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Curso;
import com.nadic.desafiobackend.entities.Disciplina;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    List<AlunosAllDto> toDtoList(List<Aluno> alunos);

    AlunoDto toDto(Aluno aluno);

    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "disciplinas", ignore = true)
    Aluno toEntity(NewAlunoDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(NewAlunoDto dto, @MappingTarget Aluno aluno);

    default String map(Curso curso) {
        return curso != null ? curso.getNome() : null;
    }

    default List<String> map(List<Disciplina> disciplinas) {
        return disciplinas != null ? disciplinas.stream().map(Disciplina::getNome).toList() : null;
    }
}

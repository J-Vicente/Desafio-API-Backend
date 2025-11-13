package com.nadic.desafiobackend.dtos.mappers;


import com.nadic.desafiobackend.dtos.curso.CursoDto;
import com.nadic.desafiobackend.dtos.curso.NewCursoDto;
import com.nadic.desafiobackend.entities.Curso;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {
    List<CursoDto> toDtoList(List<Curso> cursos);

    CursoDto toDto(Curso curso);

    Curso toEntity(NewCursoDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(NewCursoDto dto, @MappingTarget Curso curso);

}
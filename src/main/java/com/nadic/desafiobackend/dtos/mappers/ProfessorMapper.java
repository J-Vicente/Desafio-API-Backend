package com.nadic.desafiobackend.dtos.mappers;

import com.nadic.desafiobackend.dtos.professor.NewProfessorDto;
import com.nadic.desafiobackend.dtos.professor.ProfessorDto;
import com.nadic.desafiobackend.entities.Professor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    List<ProfessorDto> toDtoList(List<Professor> professores);

    ProfessorDto toDto(Professor professor);

    Professor toEntity(NewProfessorDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(NewProfessorDto dto, @MappingTarget Professor professor);

}




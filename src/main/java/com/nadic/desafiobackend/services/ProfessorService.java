package com.nadic.desafiobackend.services;

import com.nadic.desafiobackend.dtos.mappers.AlunoMapper;
import com.nadic.desafiobackend.dtos.mappers.ProfessorMapper;
import com.nadic.desafiobackend.dtos.professor.NewProfessorDto;
import com.nadic.desafiobackend.dtos.professor.ProfessorDto;
import com.nadic.desafiobackend.entities.Disciplina;
import com.nadic.desafiobackend.entities.Professor;
import com.nadic.desafiobackend.repositories.DisciplinaRepository;
import com.nadic.desafiobackend.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorMapper professorMapper;

    @Transactional
    public List<ProfessorDto> findAll() {
        return professorMapper.toDtoList(professorRepository.findAll());
    }


    @Transactional
    public void create(NewProfessorDto newProfessor) {
        professorRepository.save(professorMapper.toEntity(newProfessor));
    }

    @Transactional()
    public ProfessorDto findById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        return professorMapper.toDto(professor);
    }

    @Transactional
    public ProfessorDto update(Long id, NewProfessorDto dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        professorMapper.updateFromDto(dto, professor);

        return professorMapper.toDto(professorRepository.save(professor));
    }

    @Transactional
    public void delete(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        List<Disciplina> disciplinas = disciplinaRepository.findAllByProfessoresId(id);

        for (Disciplina d : disciplinas) {
            d.getProfessores().remove(professor);
        }

        disciplinaRepository.saveAll(disciplinas);

        professorRepository.delete(professor);

    }

    @Transactional
    public List<ProfessorDto> findByDisciplinaId(Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrado"));

        return professorMapper.toDtoList(disciplina.getProfessores());
    }

    @Transactional
    public List<ProfessorDto> findByNome(String query) {
        return professorMapper.toDtoList(professorRepository.findByNomeContainingIgnoreCase(query));
    }
}

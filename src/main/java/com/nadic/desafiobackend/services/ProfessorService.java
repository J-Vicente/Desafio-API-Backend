package com.nadic.desafiobackend.services;

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

    @Transactional
    public List<ProfessorDto> findAll() {
        List<Professor> professores = professorRepository.findAll();
        return professores.stream().map(ProfessorDto::new).toList();
    }


    @Transactional
    public void create(NewProfessorDto newProfessor) {

        Professor professor = new Professor();
        professor.setNome(newProfessor.getNome());
        professor.setMatricula(newProfessor.getMatricula());

        professorRepository.save(professor);
    }

    @Transactional()
    public ProfessorDto findById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        return new ProfessorDto(professor);
    }

    @Transactional
    public ProfessorDto update(Long id, NewProfessorDto dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            professor.setNome(dto.getNome());
        }

        if (dto.getMatricula() != null) {
            professor.setMatricula(dto.getMatricula());
        }

        return new ProfessorDto(professorRepository.save(professor));
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
        List<Professor> professores = disciplina.getProfessores();
        return professores.stream().map(ProfessorDto::new).toList();
    }

    @Transactional
    public List<ProfessorDto> findByNome(String query) {
        List<Professor> professores = professorRepository.findByNomeContainingIgnoreCase(query);
        return professores.stream().map(ProfessorDto::new).toList();
    }
}

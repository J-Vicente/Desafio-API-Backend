package com.nadic.desafiobackend.services;

import com.nadic.desafiobackend.dtos.disciplina.DisciplinaAllDto;
import com.nadic.desafiobackend.dtos.disciplina.DisciplinaDto;
import com.nadic.desafiobackend.dtos.disciplina.NewDisciplinaDto;
import com.nadic.desafiobackend.dtos.mappers.DisciplinaMapper;
import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Curso;
import com.nadic.desafiobackend.entities.Disciplina;
import com.nadic.desafiobackend.entities.Professor;
import com.nadic.desafiobackend.repositories.AlunoRepository;
import com.nadic.desafiobackend.repositories.CursoRepository;
import com.nadic.desafiobackend.repositories.DisciplinaRepository;
import com.nadic.desafiobackend.repositories.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    @Transactional
    public List<DisciplinaAllDto> findAll() {
        return disciplinaMapper.toDtoList(disciplinaRepository.findAll());
    }

    @Transactional
    public void create(NewDisciplinaDto newDisciplina) {
        Curso curso = cursoRepository.findById(newDisciplina.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        List<Professor> professores = professorRepository.findAllById(newDisciplina.getProfessores());

        List<Aluno> alunos = alunoRepository.findAllById(newDisciplina.getAlunos());

        Disciplina disciplina = disciplinaMapper.toEntity(newDisciplina);
        disciplina.setCurso(curso);
        disciplina.setProfessores(professores);
        disciplina.setAlunos(alunos);

        for(Aluno aluno : alunos){
            List <Disciplina> lista = aluno.getDisciplinas();
            lista.add(disciplina);
            aluno.setDisciplinas(lista);
        }

        disciplinaRepository.save(disciplina);
    }

    @Transactional()
    public DisciplinaDto findById(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        return disciplinaMapper.toDto(disciplina);
    }

    @Transactional
    public DisciplinaDto update(Long id, NewDisciplinaDto dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        disciplinaMapper.updateFromDto(dto, disciplina);

        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

            disciplina.setCurso(curso);
        }

        if (dto.getProfessores() != null && !dto.getProfessores().isEmpty()) {
            List<Professor> professores = professorRepository.findAllById(dto.getProfessores());

            disciplina.setProfessores(professores);
        }

        if (dto.getAlunos() != null && !dto.getAlunos().isEmpty()) {
            List<Aluno> alunos = alunoRepository.findAllById(dto.getAlunos());

            disciplina.setAlunos(alunos);
        }

        return disciplinaMapper.toDto(disciplinaRepository.save(disciplina));
    }

    @Transactional
    public void delete(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        if (disciplina.getProfessores() != null) {
            disciplina.getProfessores().clear();
        }

        if (disciplina.getAlunos() != null) {
            for (Aluno aluno : disciplina.getAlunos()) {
                aluno.getDisciplinas().remove(disciplina);
            }
            alunoRepository.saveAll(disciplina.getAlunos());
            disciplina.getAlunos().clear();
        }

        disciplina.setCurso(null);

        disciplinaRepository.delete(disciplina);
    }


    @Transactional
    public List<DisciplinaAllDto> findByCursoId(Long cursoId) {
        return disciplinaMapper.toDtoList(disciplinaRepository.findByCursoId(cursoId));
    }
}

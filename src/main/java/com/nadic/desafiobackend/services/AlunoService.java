package com.nadic.desafiobackend.services;

import com.nadic.desafiobackend.dtos.aluno.AlunoDto;
import com.nadic.desafiobackend.dtos.aluno.AlunosAllDto;
import com.nadic.desafiobackend.dtos.aluno.NewAlunoDto;
import com.nadic.desafiobackend.dtos.mappers.AlunoMapper;
import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Curso;
import com.nadic.desafiobackend.entities.Disciplina;
import com.nadic.desafiobackend.repositories.AlunoRepository;
import com.nadic.desafiobackend.repositories.CursoRepository;
import com.nadic.desafiobackend.repositories.DisciplinaRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoMapper alunoMapper;

    @Transactional
    public List<AlunosAllDto> findAll() {
        return alunoMapper.toDtoList(alunoRepository.findAll());
    }


    @Transactional
    public void create(NewAlunoDto newAluno) {
        Aluno aluno = alunoMapper.toEntity(newAluno);

        Curso curso = cursoRepository.findById(newAluno.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        aluno.setCurso(curso);

        List<Disciplina> disciplinas = disciplinaRepository.findAllById(newAluno.getDisciplinasId());

        aluno.setDisciplinas(disciplinas);

        alunoRepository.save(aluno);
    }

    @Transactional()
    public AlunoDto findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return alunoMapper.toDto(aluno);
    }

    @Transactional
    public AlunoDto update(Long id, NewAlunoDto dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        alunoMapper.updateFromDto(dto, aluno);

        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            aluno.setCurso(curso);
        }

        if (dto.getDisciplinasId() != null && !dto.getDisciplinasId().isEmpty()) {
            List<Disciplina> disciplinas = disciplinaRepository.findAllById(dto.getDisciplinasId());
            aluno.setDisciplinas(disciplinas);
        }


        return alunoMapper.toDto(alunoRepository.save(aluno));
    }

    @Transactional
    public void delete(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado");
        }

        alunoRepository.deleteById(id);
    }

    @Transactional
    public List<AlunosAllDto> findByCursoId(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        return alunoMapper.toDtoList(alunoRepository.findByCurso(curso));
    }

    @Transactional
    public List<AlunosAllDto> search(String query) {
        return alunoMapper.toDtoList(alunoRepository.findByQuery(query));
    }
}
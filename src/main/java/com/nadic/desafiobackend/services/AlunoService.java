package com.nadic.desafiobackend.services;

import com.nadic.desafiobackend.dtos.aluno.AlunoDto;
import com.nadic.desafiobackend.dtos.aluno.AlunosAllDto;
import com.nadic.desafiobackend.dtos.aluno.NewAlunoDto;
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

    @Transactional
    public List<AlunosAllDto> findAll() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream().map(AlunosAllDto::new).toList();
    }


    @Transactional
    public void create(NewAlunoDto newAluno) {
        Curso curso = cursoRepository.findById(newAluno.getCurso())
                .orElseThrow(() -> new RuntimeException("Usuário criador não encontrado"));

        List<Disciplina> disciplinas = disciplinaRepository.findAllById(newAluno.getDisciplinas_id());

        Aluno aluno = new Aluno();
        aluno.setNome(newAluno.getNome());
        aluno.setMatricula(newAluno.getMatricula());
        aluno.setCurso(curso);
        aluno.setDisciplinas(disciplinas);

        alunoRepository.save(aluno);
    }

    @Transactional()
    public AlunoDto findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return new AlunoDto(aluno);
    }

    @Transactional
    public AlunoDto update(Long id, NewAlunoDto dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (dto.getCurso() != null) {
            Curso curso = cursoRepository.findById(dto.getCurso())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            aluno.setCurso(curso);
        }

        if (dto.getDisciplinas_id() != null && !dto.getDisciplinas_id().isEmpty()) {
            List<Disciplina> disciplinas = disciplinaRepository.findAllById(dto.getDisciplinas_id());
            aluno.setDisciplinas(disciplinas);
        }

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            aluno.setNome(dto.getNome());
        }

        if (dto.getMatricula() != null) {
            aluno.setMatricula(dto.getMatricula());
        }

        return new AlunoDto(alunoRepository.save(aluno));
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
        List<Aluno> alunos = alunoRepository.findByCurso(curso);
        return alunos.stream().map(AlunosAllDto::new).toList();
    }

    @Transactional
    public List<AlunosAllDto> search(String query) {
        List<Aluno> alunos = alunoRepository.findByQuery(query);
        return alunos.stream().map(AlunosAllDto::new).toList();
    }
}
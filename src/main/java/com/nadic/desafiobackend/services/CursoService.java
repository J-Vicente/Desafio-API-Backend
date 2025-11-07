package com.nadic.desafiobackend.services;

import com.nadic.desafiobackend.dtos.curso.CursoDto;
import com.nadic.desafiobackend.dtos.curso.NewCursoDto;
import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Curso;
import com.nadic.desafiobackend.entities.Disciplina;
import com.nadic.desafiobackend.repositories.AlunoRepository;
import com.nadic.desafiobackend.repositories.CursoRepository;

import com.nadic.desafiobackend.repositories.DisciplinaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public List<CursoDto> findAll() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream().map(CursoDto::new).toList();
    }


    @Transactional
    public void create(NewCursoDto newCurso) {

        Curso curso = new Curso();
        curso.setNome(newCurso.getNome());
        curso.setCodigo(newCurso.getCodigo());

        cursoRepository.save(curso);
    }

    @Transactional()
    public CursoDto findById(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        return new CursoDto(curso);
    }

    @Transactional
    public CursoDto update(Long id, NewCursoDto dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            curso.setNome(dto.getNome());
        }

        if (dto.getCodigo() != null) {
            curso.setCodigo(dto.getCodigo());
        }

        return new CursoDto(cursoRepository.save(curso));
    }

    @Transactional
    public void delete(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        List<Aluno> alunos = alunoRepository.findAllByCursoId(id);
        for (Aluno aluno : alunos) {
            aluno.setCurso(null);
        }
        alunoRepository.saveAll(alunos);

        List<Disciplina> disciplinas = disciplinaRepository.findAllByCursoId(id);
        for (Disciplina disciplina : disciplinas) {
            disciplina.setCurso(null);
        }
        disciplinaRepository.saveAll(disciplinas);

        cursoRepository.delete(curso);
    }


    @Transactional
    public List<CursoDto> findByNome(String query) {
        List<Curso> curso = cursoRepository.findByNomeContainingIgnoreCase(query);
        return curso.stream().map(CursoDto::new).toList();
    }
}

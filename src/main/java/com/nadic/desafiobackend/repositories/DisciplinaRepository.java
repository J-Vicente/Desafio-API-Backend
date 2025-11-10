package com.nadic.desafiobackend.repositories;

import com.nadic.desafiobackend.entities.Disciplina;
import com.nadic.desafiobackend.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findAllByProfessoresId(Long professorId);

    List<Disciplina> findAllByCursoId(Long cursoId);

    List<Disciplina> findByCursoId(Long cursoId);

}

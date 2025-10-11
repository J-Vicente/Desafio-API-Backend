package com.nadic.desafiobackend.repositories;

import com.nadic.desafiobackend.entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findAllByProfessores_Id(Long professorId);

    List<Disciplina> findAllByCurso_Id(Long cursoId);

}

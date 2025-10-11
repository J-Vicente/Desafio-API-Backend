package com.nadic.desafiobackend.repositories;

import com.nadic.desafiobackend.entities.Disciplina;
import com.nadic.desafiobackend.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByNomeContainingIgnoreCase(String nome);
}

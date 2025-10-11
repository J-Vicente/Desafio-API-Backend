package com.nadic.desafiobackend.repositories;

import com.nadic.desafiobackend.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByNomeContainingIgnoreCase(String nome);
}

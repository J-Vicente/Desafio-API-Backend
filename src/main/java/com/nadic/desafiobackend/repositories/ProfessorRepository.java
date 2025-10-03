package com.nadic.desafiobackend.repositories;

import com.nadic.desafiobackend.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}

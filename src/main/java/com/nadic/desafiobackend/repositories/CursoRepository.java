package com.nadic.desafiobackend.repositories;

import com.nadic.desafiobackend.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso, Long> {
}

package com.nadic.desafiobackend.repositories;

import com.nadic.desafiobackend.entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}

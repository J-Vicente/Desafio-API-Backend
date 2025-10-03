package com.nadic.desafiobackend.repositories;

import com.nadic.desafiobackend.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}

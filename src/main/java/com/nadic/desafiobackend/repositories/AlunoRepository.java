package com.nadic.desafiobackend.repositories;

import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByCurso(Curso curso);

    @Query("SELECT a FROM Aluno a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR CAST(a.matricula AS string) LIKE CONCAT('%', :query, '%')")
    List<Aluno> findByQuery(@Param("query") String query);
}

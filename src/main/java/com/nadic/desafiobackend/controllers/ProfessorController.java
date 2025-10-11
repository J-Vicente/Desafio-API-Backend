package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.professor.NewProfessorDto;
import com.nadic.desafiobackend.dtos.professor.ProfessorDto;
import com.nadic.desafiobackend.dtos.response.ApiResponse;
import com.nadic.desafiobackend.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/professores")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProfessorDto>>> getProfessores(
            @RequestParam(value = "disciplina", required = false) Long disciplinaId,
            @RequestParam(value = "nome", required = false) String nome) {

        List<ProfessorDto> professores;

        if (disciplinaId != null) {
            professores = professorService.findByDisciplinaId(disciplinaId);
        }
        else if (nome != null && !nome.isBlank()) {
            professores = professorService.findByNome(nome);
        }
        else {
            professores = professorService.findAll();
        }

        ApiResponse<List<ProfessorDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Requisição completada com sucesso");
        response.setData(professores);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createProfesor(@Valid @RequestBody NewProfessorDto dto) {
        professorService.create(dto);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Professor criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfessorDto>> getProfessorById(@PathVariable Long id) {
        ProfessorDto professor = professorService.findById(id);

        ApiResponse<ProfessorDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setData(professor);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfessorDto>> updateProfessor(@PathVariable Long id,
                                                             @Valid @RequestBody NewProfessorDto dto) {

        ProfessorDto professor = professorService.update(id, dto);

        ApiResponse<ProfessorDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Professor atualizado com sucesso");
        response.setData(professor);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProfessor(@PathVariable Long id) {
        professorService.delete(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Professor removido com sucesso");

        return ResponseEntity.ok(response);
    }

}

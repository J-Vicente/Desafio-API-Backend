package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.disciplina.DisciplinaAllDto;
import com.nadic.desafiobackend.dtos.disciplina.DisciplinaDto;
import com.nadic.desafiobackend.dtos.disciplina.NewDisciplinaDto;
import com.nadic.desafiobackend.dtos.response.ApiResponse;
import com.nadic.desafiobackend.services.DisciplinaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DisciplinaAllDto>>> getDisciplinas(
            @RequestParam(value = "curso", required = false) Long cursoId) {

        List<DisciplinaAllDto> disciplinas;

        if (cursoId != null) {
            disciplinas = disciplinaService.findByCursoId(cursoId);

        } else {
            disciplinas = disciplinaService.findAll();
        }

        ApiResponse<List<DisciplinaAllDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Requisição completada com sucesso");
        response.setData(disciplinas);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createDisciplina(@Valid @RequestBody NewDisciplinaDto dto) {
        disciplinaService.create(dto);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Disciplina criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DisciplinaDto>> getDisciplinaById(@PathVariable Long id) {
        DisciplinaDto disciplina = disciplinaService.findById(id);

        ApiResponse<DisciplinaDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setData(disciplina);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DisciplinaDto>> updateDisciplina(@PathVariable Long id,
                                                                       @Valid @RequestBody NewDisciplinaDto dto) {
        DisciplinaDto disciplina = disciplinaService.update(id, dto);

        ApiResponse<DisciplinaDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Disciplina atualizada com sucesso");
        response.setData(disciplina);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDisciplina(@PathVariable Long id) {
        disciplinaService.delete(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Disciplina removida com sucesso");

        return ResponseEntity.ok(response);
    }
}

package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.disciplina.DisciplinaAllDto;
import com.nadic.desafiobackend.dtos.disciplina.DisciplinaDto;
import com.nadic.desafiobackend.dtos.disciplina.NewDisciplinaDto;
import com.nadic.desafiobackend.dtos.response.ApiResponseDto;
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
    public ResponseEntity<ApiResponseDto<List<DisciplinaAllDto>>> getDisciplinas(
            @RequestParam(value = "curso", required = false) Long cursoId) {

        List<DisciplinaAllDto> disciplinas;

        if (cursoId != null) {
            disciplinas = disciplinaService.findByCursoId(cursoId);

        } else {
            disciplinas = disciplinaService.findAll();
        }

        ApiResponseDto<List<DisciplinaAllDto>> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Requisição completada com sucesso");
        response.setData(disciplinas);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> createDisciplina(@Valid @RequestBody NewDisciplinaDto dto) {
        disciplinaService.create(dto);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Disciplina criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<DisciplinaDto>> getDisciplinaById(@PathVariable Long id) {
        DisciplinaDto disciplina = disciplinaService.findById(id);

        ApiResponseDto<DisciplinaDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setData(disciplina);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<DisciplinaDto>> updateDisciplina(@PathVariable Long id,
                                                                          @Valid @RequestBody NewDisciplinaDto dto) {
        DisciplinaDto disciplina = disciplinaService.update(id, dto);

        ApiResponseDto<DisciplinaDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Disciplina atualizada com sucesso");
        response.setData(disciplina);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteDisciplina(@PathVariable Long id) {
        disciplinaService.delete(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Disciplina removida com sucesso");

        return ResponseEntity.ok(response);
    }
}

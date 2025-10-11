package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.curso.CursoDto;
import com.nadic.desafiobackend.dtos.curso.NewCursoDto;
import com.nadic.desafiobackend.dtos.response.ApiResponse;
import com.nadic.desafiobackend.services.CursoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CursoDto>>> getCursos(
            @RequestParam(value = "nome", required = false) String nome) {

        List<CursoDto> cursos;

        if (nome != null && !nome.isBlank()) {
            cursos = cursoService.findByNome(nome);

        } else {
            cursos = cursoService.findAll();
        }

        ApiResponse<List<CursoDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Requisição completada com sucesso");
        response.setData(cursos);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createCurso(@Valid @RequestBody NewCursoDto dto) {
        cursoService.create(dto);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Curso criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CursoDto>> getCursoById(@PathVariable Long id) {
        CursoDto curso = cursoService.findById(id);

        ApiResponse<CursoDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setData(curso);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CursoDto>> updateCurso(@PathVariable Long id,
                                                             @Valid @RequestBody NewCursoDto dto) {

        CursoDto curso = cursoService.update(id, dto);

        ApiResponse<CursoDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Curso atualizado com sucesso");
        response.setData(curso);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCurso(@PathVariable Long id) {
        cursoService.delete(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Curso removido com sucesso");

        return ResponseEntity.ok(response);
    }
}

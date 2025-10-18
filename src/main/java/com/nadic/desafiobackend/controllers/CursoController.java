package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.curso.CursoDto;
import com.nadic.desafiobackend.dtos.curso.NewCursoDto;
import com.nadic.desafiobackend.dtos.response.ApiResponseDto;
import com.nadic.desafiobackend.services.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cursos")
@Tag(name = "Curso", description = "Endpoinnts para gerenciamento de cursos.")
@Order(4)
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Operation(summary ="",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "", description = ""),
            @ApiResponse(responseCode = "", description = "",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<CursoDto>>> getCursos(
            @RequestParam(value = "nome", required = false) String nome) {

        List<CursoDto> cursos;

        if (nome != null && !nome.isBlank()) {
            cursos = cursoService.findByNome(nome);

        } else {
            cursos = cursoService.findAll();
        }

        ApiResponseDto<List<CursoDto>> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Requisição completada com sucesso");
        response.setData(cursos);

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "", description = ""),
            @ApiResponse(responseCode = "", description = "",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> createCurso(@Valid @RequestBody NewCursoDto dto) {
        cursoService.create(dto);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Curso criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary ="",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "", description = ""),
            @ApiResponse(responseCode = "", description = "",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CursoDto>> getCursoById(@PathVariable Long id) {
        CursoDto curso = cursoService.findById(id);

        ApiResponseDto<CursoDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setData(curso);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "", description = ""),
            @ApiResponse(responseCode = "", description = "",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CursoDto>> updateCurso(@PathVariable Long id,
                                                                @Valid @RequestBody NewCursoDto dto) {

        CursoDto curso = cursoService.update(id, dto);

        ApiResponseDto<CursoDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Curso atualizado com sucesso");
        response.setData(curso);

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "", description = ""),
            @ApiResponse(responseCode = "", description = "",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteCurso(@PathVariable Long id) {
        cursoService.delete(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Curso removido com sucesso");

        return ResponseEntity.ok(response);
    }
}

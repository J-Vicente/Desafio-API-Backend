package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.curso.CursoDto;
import com.nadic.desafiobackend.dtos.curso.NewCursoDto;
import com.nadic.desafiobackend.dtos.response.ApiResponseDto;
import com.nadic.desafiobackend.services.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(description ="Retorna uma lista de dtos de todos os cursos, mostrando o Id, nome e código. " +
            "Caso receba uma query, que deve ser uma string, " +
            "buscará correspondencias dentre os nomes dos cursos.",
            summary = "Lista todos os cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, Lista retornada com sucesso"),
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<CursoDto>>> getCursos(
            @Parameter(description = "Nome ou parte do nome do curso a ser buscado")
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

    @Operation(summary ="Cadastra um novo curso",
            description = "Recebe um dto com nome e código, " +
                    "caso todos campos sejam válidos criará um novo curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso, Curso criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito, campo que deveria ser único duplicado"),
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> createCurso(@Valid @RequestBody NewCursoDto dto) {
        cursoService.create(dto);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Curso criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary ="Retorna um curso específicado por Id",
            description = "Recebe um Long Id e busca se há algum curso correspondente,"+
                    "caso tenha retorna um dto com todas as informações desse curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, curso encontrado com sucesso"),

    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CursoDto>> getCursoById(@Parameter(description = "Id do curso a ser buscado")
                                                                     @PathVariable Long id) {
        CursoDto curso = cursoService.findById(id);

        ApiResponseDto<CursoDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setData(curso);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Altera as informações de um curso indicado",
            description = "Recebe um Long Id para identificar o curso a ser alterado, " +
                    "caso encontre recebe dto com os campos a serem alterados, " +
                    "é possível alterar todos ou apenas os desejados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso, curso cadastrado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito, campo que deve ser único duplicado",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CursoDto>> updateCurso(@Parameter(description = "Id do curso a ser atualizado")
                                                                    @PathVariable Long id,
                                                                @Valid @RequestBody NewCursoDto dto) {

        CursoDto curso = cursoService.update(id, dto);

        ApiResponseDto<CursoDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Curso atualizado com sucesso");
        response.setData(curso);

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Exclui um curso indicado",
            description = "Recebe um Long Id e caso encontre o curso específicado deleta ele.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, curso deletado com sucesso"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteCurso(@Parameter(description = "Id do curso a ser excluído")
                                                                @PathVariable Long id) {
        cursoService.delete(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Curso removido com sucesso");

        return ResponseEntity.ok(response);
    }
}

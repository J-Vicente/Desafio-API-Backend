package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.disciplina.DisciplinaAllDto;
import com.nadic.desafiobackend.dtos.disciplina.DisciplinaDto;
import com.nadic.desafiobackend.dtos.disciplina.NewDisciplinaDto;
import com.nadic.desafiobackend.dtos.response.ApiResponseDto;
import com.nadic.desafiobackend.services.DisciplinaService;

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
@RequestMapping("api/v1/disciplinas")
@Tag(name = "Disciplinas", description = "Endpoints para gerenciamento de disciplinas.")
@Order(5)
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @Operation(summary ="Lista todas as disciplinas cadastradas",
            description = "Retorna uma lista de dtos de todos as disciplinas, mostrando o Id, nome, codigo, e curso." +
                    "Caso seja passado o parametro de cursoId retornará apenas as disciplinas daquele curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, lista retornada com sucesso"),
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<DisciplinaAllDto>>> getDisciplinas(
            @Parameter(description = "Nome do curso para ser filtrado")
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

    @Operation(summary ="Cadastrar nova disciplina",
            description = "Recebe um dto com nome, código, Id de um curso e listas com ids de professores e alunos, " +
                    "caso todos campos sejam válidos criará um novo aluno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso, disciplina cadastrada com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito, campo que deve ser único duplicado"),
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> createDisciplina(@Valid @RequestBody NewDisciplinaDto dto) {
        disciplinaService.create(dto);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Disciplina criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary ="Retorna uma disciplina específica",
            description = "Recebe um Long Id e busca se há alguma disciplina correspondente," +
                    "caso tenha retorna um dto com todas as informações dessa disciplina.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, Disciplina encontrada com sucesso."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<DisciplinaDto>> getDisciplinaById(
                                                        @Parameter(description = "Id da dsiciplia a ser buscada")
                                                        @PathVariable Long id) {
        DisciplinaDto disciplina = disciplinaService.findById(id);

        ApiResponseDto<DisciplinaDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setData(disciplina);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Altera uma disciplina indicada",
            description = "Recebe um Long Id para identificar a disciplina a ser alterada, " +
                    "caso encontre recebe dto com os campos a serem alterados, " +
                    "é possível alterar todos ou apenas os desejados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso, disciplina alterada com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito, um campo que deve ser único está duplicado",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<DisciplinaDto>> updateDisciplina(
                                                        @Parameter(description = "Id da dsiciplina a ser atualizada")
                                                        @PathVariable Long id,
                                                        @Valid @RequestBody NewDisciplinaDto dto) {

        DisciplinaDto disciplina = disciplinaService.update(id, dto);

        ApiResponseDto<DisciplinaDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Disciplina atualizada com sucesso");
        response.setData(disciplina);

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Exclui uma disciplina indicada",
            description = "Recebe um Long Id e caso encontre a disciplina específicada deleta ela.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, disciplina excluida com sucesso."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteDisciplina(
                                                @Parameter(description = "Id da disciplina a ser excluida")
                                                @PathVariable Long id) {
        disciplinaService.delete(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Disciplina removida com sucesso");

        return ResponseEntity.ok(response);
    }
}

package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.aluno.AlunoDto;
import com.nadic.desafiobackend.dtos.aluno.AlunosAllDto;
import com.nadic.desafiobackend.dtos.aluno.NewAlunoDto;
import com.nadic.desafiobackend.dtos.response.ApiResponseDto;
import com.nadic.desafiobackend.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/alunos")
@Tag(name="Alunos", description="Endpoints para gerenciamento de alunos.")
@Order(2)
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(summary = "Lista todos os alunos cadastrados.",
            description = "Retorna uma lista de dtos de todos os alunos, mostrando o Id, nome, matrícula, e curso. " +
                    "Caso seja passado o parametro de cursoId retornará apenas os alunos daquele curso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, Lista retornada"),
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<AlunosAllDto>>> getAlunos(
            @Parameter(description = "Id do curso a ser filtrado")
            @RequestParam(value = "curso", required = false) Long cursoId) {

        List<AlunosAllDto> alunos;
        if (cursoId != null) {
            alunos = alunoService.findByCursoId(cursoId);
        } else {
            alunos = alunoService.findAll();
        }

        ApiResponseDto<List<AlunosAllDto>> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Requisição completada com sucesso");
        response.setData(alunos);

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Cadastra um novo aluno.",
                description = "Recebe um dto com nome, matricula, Id de um curso e uma lista de ids de disciplinas, " +
                        "caso todos campos sejam válidos criará um novo aluno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso, Aluno criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito, Campo que deve ser único duplicado"),
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> createAluno(@RequestBody @Valid  NewAlunoDto dto) {
        alunoService.create(dto);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Aluno criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Retorna um aluno especificado por Id.",
            description = "Recebe um Long Id e busca se há algum aluno correspondente, " +
                    "caso tenha retorna um dto com todas as informações desse aluno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, Aluno retornado com sucesso"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AlunoDto>> getAlunoById(@Parameter(description = "Id do aluno a ser buscado")
                                                    @PathVariable Long id) {
        AlunoDto aluno = alunoService.findById(id);

        ApiResponseDto<AlunoDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setData(aluno);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Altera as informações de um aluno.",
            description = "Recebe um Long Id para identificar o aluno a ser alterado, " +
                    "caso encontre recebe dto com os campos a serem alterados, " +
                    "é possível alterar todos ou apenas os desejados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso, Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito, Campo que deve ser único duplicado",
            content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AlunoDto>> updateAluno(@PathVariable Long id,
                                                                @Valid @RequestBody NewAlunoDto dto) {

        AlunoDto aluno = alunoService.update(id, dto);

        ApiResponseDto<AlunoDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Aluno atualizado com sucesso");
        response.setData(aluno);

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Exclui um aluno informado.",
            description = "Recebe um Long Id e caso encontre o aluno específicado deleta ele.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, Aluno deletado com sucesso"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteAluno(@PathVariable Long id) {
        alunoService.delete(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Aluno removido com sucesso");

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Pesquisa um aluno pelo nome ou matrícula.",
            description = "Recebe uma query que pode ser uma string ou número inteiro. " +
                    "Se for uma string buscará correspondencias dentre os nomes de alunos, " +
                    "diferenciando letras minúsculas de maiúsculas. " +
                    "Se for um inteiro buscará correpondências entre as matriculas de alunos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, Lista retornada com sucesso"),
    })
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDto<List<AlunosAllDto>>> searchAlunos(
            @RequestParam("q") String query) {

        List<AlunosAllDto> alunos = alunoService.search(query);

        ApiResponseDto<List<AlunosAllDto>> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Busca realizada com sucesso");
        response.setData(alunos);

        return ResponseEntity.ok(response);
    }
}

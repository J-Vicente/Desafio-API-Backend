package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.professor.NewProfessorDto;
import com.nadic.desafiobackend.dtos.professor.ProfessorDto;
import com.nadic.desafiobackend.dtos.response.ApiResponseDto;
import com.nadic.desafiobackend.services.ProfessorService;
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
@RequestMapping("api/v1/professores")
@Tag(name = "Professores", description = "Endpoints para gerenciamento de professores.")
@Order(3)
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @Operation(summary ="Lista todos os professores",
            description = "Retorna uma lista de dtos de todos os professoress, mostrando o Id, nome e matrícula." +
                    "Caso seja passado o parametro de disciplina, retornará apenas os professoress daquela disciplina." +
                    "Caso receba uma query, que deve ser uma string, " +
                    "buscará correspondencias dentre os nomes de professores.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, Lista retornnada com sucesso"),
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ProfessorDto>>> getProfessores(
            @Parameter(description = "Id da disiciplina a ser filtrada")
            @RequestParam(value = "disciplina", required = false) Long disciplinaId,
            @Parameter(description = "Nome ou parte do nome do professor a ser buscado")
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

        ApiResponseDto<List<ProfessorDto>> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Requisição completada com sucesso");
        response.setData(professores);

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Cadastra um novo professor",
            description = "Recebe um dto com nome e matricula, " +
                    "caso todos campos sejam válidos criará um novo professor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso, professor criado comm sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito, campo que deve ser único duplicado")
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> createProfesor(@Valid @RequestBody NewProfessorDto dto) {
        professorService.create(dto);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Professor criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary ="Retorna um professor específicado por Id",
            description = "Recebe um Long Id e busca se há algum professor correspondente, "+
                    "caso tenha retorna um dto com todas as informações desse professor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, professor encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProfessorDto>> getProfessorById(
                                        @Parameter(description = "Id do professor a ser buscado")
                                        @PathVariable Long id) {

        ProfessorDto professor = professorService.findById(id);

        ApiResponseDto<ProfessorDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setData(professor);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Altera as informações de um professor",
            description = "Recebe um Long Id para identificar o professor a ser alterado," +
                    "caso encontre recebe dto com os campos a serem alterados," +
                    "é possível alterar todos ou apenas os desejados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso, professor atualizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito, campo que deve ser único duplicado",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProfessorDto>> updateProfessor(
            @Parameter(description = "Id do professor a ser atualizado")
            @PathVariable Long id,
            @Valid @RequestBody NewProfessorDto dto) {

        ProfessorDto professor = professorService.update(id, dto);

        ApiResponseDto<ProfessorDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Professor atualizado com sucesso");
        response.setData(professor);

        return ResponseEntity.ok(response);
    }

    @Operation(summary ="Exlui um professor indicado",
            description = "Recebe um Long Id e caso encontre o professor específicado deleta ele.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, professor deletado com sucesso"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteProfessor(
                                                    @Parameter(description = "Id do professor a ser deletado")
                                                    @PathVariable Long id) {
        professorService.delete(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setMessage("Professor removido com sucesso");

        return ResponseEntity.ok(response);
    }

}

package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.aluno.AlunoDto;
import com.nadic.desafiobackend.dtos.aluno.AlunosAllDto;
import com.nadic.desafiobackend.dtos.aluno.NewAlunoDto;
import com.nadic.desafiobackend.dtos.response.ApiResponse;
import com.nadic.desafiobackend.entities.Aluno;
import com.nadic.desafiobackend.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AlunosAllDto>>> getAlunos(
            @RequestParam(value = "curso", required = false) Long cursoId) {

        List<AlunosAllDto> alunos;
        if (cursoId != null) {
            alunos = alunoService.findByCursoId(cursoId);
        } else {
            alunos = alunoService.findAll();
        }

        ApiResponse<List<AlunosAllDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Requisição completada com sucesso");
        response.setData(alunos);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createAluno(@Valid @RequestBody NewAlunoDto dto) {
        alunoService.create(dto);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Aluno criada com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AlunoDto>> getAlunoById(@PathVariable Long id) {
        AlunoDto aluno = alunoService.findById(id);

        ApiResponse<AlunoDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setData(aluno);
        response.setMessage("Requisição completada com sucesso");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AlunoDto>> updateAluno(@PathVariable Long id,
                                                             @Valid @RequestBody NewAlunoDto dto) {

        AlunoDto aluno = alunoService.update(id, dto);

        ApiResponse<AlunoDto> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Aluno atualizado com sucesso");
        response.setData(aluno);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAluno(@PathVariable Long id) {
        alunoService.delete(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Aluno removido com sucesso");

        return ResponseEntity.ok(response);
    }

    // GET pesquisa full-text
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<AlunosAllDto>>> searchAlunos(
            @RequestParam("q") String query) {

        List<AlunosAllDto> alunos = alunoService.search(query);

        ApiResponse<List<AlunosAllDto>> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMessage("Busca realizada com sucesso");
        response.setData(alunos);

        return ResponseEntity.ok(response);
    }
}

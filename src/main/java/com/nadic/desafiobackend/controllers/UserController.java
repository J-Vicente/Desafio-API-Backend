package com.nadic.desafiobackend.controllers;

import com.nadic.desafiobackend.dtos.auth.UserDto;
import com.nadic.desafiobackend.dtos.response.ApiResponseDto;
import com.nadic.desafiobackend.services.UserService;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Autenticação", description = "Endpoints de autenticação para proteger a aplicação.")
@Order(1)
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary ="Cadastrar novo usuário",
            description = "Recebe um dto com username e senha para criarii um novo usuário para o sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso, usuáriio criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro, Um usuario com o mesmo nome já existe",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PostMapping("/registration")
    public ResponseEntity<ApiResponseDto<Void>> register(@Valid @RequestBody UserDto dto) {
        userService.register(dto);

        ApiResponseDto<Void> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setData(null);
        response.setMessage("Usuário criado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary ="Gerar token de login",
            description = "Recebe username e senha para validar, caso estejam corretos, " +
                    "gera um token de acesso para o sistema que deve ser enviado junto as requisições. " +
                    "É preciso criar um usuario antes de fazer o login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, usuário logado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro, Credenciais inválidas",
                    content =  @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PostMapping("/token")
    public ResponseEntity<Map<String,String>> login(@Valid @RequestBody UserDto dto) {
        String token = userService.login(dto);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

}

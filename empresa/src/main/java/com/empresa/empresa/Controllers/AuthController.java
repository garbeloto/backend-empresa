package com.empresa.empresa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Dto.LoginDto;
import com.empresa.empresa.Dto.RegistroEmpresaDto;
import com.empresa.empresa.Dto.TokenResponse;

import com.empresa.empresa.Services.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Rota: POST /auth/login
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginDto dados) {
        // A lógica de autenticação e geração do token está toda no Service
        TokenResponse token = authService.realizarLogin(dados);
        return ResponseEntity.ok(token);
    }

    // Rota: POST /auth/register/empresa
    @PostMapping("/registrar/empresa")
    public ResponseEntity<String> registrarEmpresa(@RequestBody RegistroEmpresaDto dados) {
        // O Service cria o Usuário(ADMIN) e a Empresa
        authService.registrarEmpresa(dados);
        return ResponseEntity.ok("Empresa cadastrada com sucesso!");
    }
}

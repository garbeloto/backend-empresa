package com.empresa.empresa.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.empresa.empresa.Entities.Usuario;

import com.empresa.empresa.Services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Rota: GET /usuario/listarUsuarios
    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    // Rota: PUT /usuario/alterar-credenciais/{id}
    // Usada para alterar Email ou Senha (regras de criptografia estão no Service)
    @PutMapping("/alterar-credenciais/{idUsuario}")
    public ResponseEntity<String> alterarCredenciais(@PathVariable Long idUsuario, 
                                                    @RequestParam(required = false) String novoEmail,
                                                    @RequestParam(required = false) String novaSenha) {
        
        usuarioService.atualizarCredenciais(idUsuario, novoEmail, novaSenha);
        return ResponseEntity.ok("Credenciais atualizadas com sucesso!");
    }

    // Rota: DELETE /usuario/deletar/{id}
    @DeleteMapping("/deletar/{idUsuario}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long idUsuario) {
        try {
            usuarioService.deletarUsuario(idUsuario);
            return ResponseEntity.ok("Usuário deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
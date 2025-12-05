package com.empresa.empresa.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Dto.ColaboradorDto;
import com.empresa.empresa.Entities.Colaborador;
import com.empresa.empresa.Services.ColaboradorService;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    // Rota: POST /colaborador/cadastrarColaborador
    @PostMapping("/cadastrarColaborador")
    public ResponseEntity<String> cadastrarColaborador(@RequestBody ColaboradorDto dados) {
        // Pegamos o email da empresa logada automaticamente para vincular o colaborador a ela
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailEmpresa = auth.getName();

        // O Service cuida de verificar email único, criptografar senha e criar usuário
        colaboradorService.cadastrarColaborador(dados, emailEmpresa);

        return ResponseEntity.ok("Colaborador cadastrado com sucesso!");
    }

    // Rota: GET /colaborador/listarColaboradores
    @GetMapping("/listarColaboradores")
    public ResponseEntity<List<Colaborador>> listarColaboradores() {
        return ResponseEntity.ok(colaboradorService.listarTodos());
    }

    // Rota: GET /colaborador/buscarNomeColaborador
    @GetMapping("/buscarNomeColaborador")
    public ResponseEntity<?> buscarNomeColaborador(@RequestParam String nomeColaborador) {
        Optional<Colaborador> colaboradores = colaboradorService.buscarPorNome(nomeColaborador);
        
        if (colaboradores.isEmpty()) {
            return ResponseEntity.ok("Colaborador não encontrado");
        }
        return ResponseEntity.ok(colaboradores);
    }

    // Rota: PUT /colaborador/editarColaborador/{id}
    @PutMapping("/editarColaborador/{idColaboradorAlterar}")
    public ResponseEntity<String> editarColaborador(@PathVariable Long idColaboradorAlterar, 
                                                    @RequestBody ColaboradorDto dados) {
        try {
            colaboradorService.editarColaborador(idColaboradorAlterar, dados);
            return ResponseEntity.ok("Colaborador alterado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Rota: DELETE /colaborador/deletar/{id}
    @DeleteMapping("/deletar/{idColaborador}")
    public ResponseEntity<String> deleteForId(@PathVariable Long idColaborador) {
        try {
            colaboradorService.deletarColaborador(idColaborador);
            return ResponseEntity.ok("Colaborador deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
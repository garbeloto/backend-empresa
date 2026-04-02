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


import com.empresa.empresa.Dto.ProfissionalDto;
import com.empresa.empresa.Entities.Profissional;
import com.empresa.empresa.Services.ProfissionalService;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {
    
    @Autowired
    private ProfissionalService profissionalService;


    @PostMapping("/cadastrarProfissional")
    public ResponseEntity<String> cadastrarProfissional(@RequestBody ProfissionalDto dados) {
        // pega o email da empresa logada automaticamente para vincular o profissional a ela
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailEmpresa = auth.getName();

        profissionalService.cadastrarProfissional(dados, emailEmpresa);

        return ResponseEntity.ok("Profissional cadastrado com sucesso!");
    }


    @GetMapping("/listarProfissionais")
    public ResponseEntity<List<Profissional>> listarProfissionales() {
        return ResponseEntity.ok(profissionalService.listarTodos());
    }

    @GetMapping("/buscarNomeProfissional")
    public ResponseEntity<?> buscarNomeProfissional(@RequestParam String nomeProfissional) {
        Optional<Profissional> profissional = profissionalService.buscarPorNome(nomeProfissional);
        
        if (profissional.isEmpty()) {
            return ResponseEntity.ok("Profissional não encontrado");
        }
        return ResponseEntity.ok(profissional);
    }

    @PutMapping("/editarProfissional/{idProfissionalAlterar}")
    public ResponseEntity<String> editarProfissional(@PathVariable Integer idProfissionalAlterar, 
                                                    @RequestBody ProfissionalDto dados) {
        try {
            profissionalService.editarProfissional(idProfissionalAlterar, dados);
            return ResponseEntity.ok("Profissional alterado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{idProfissional}")
    public ResponseEntity<String> deleteForId(@PathVariable Integer idProfissional) {
        try {
            profissionalService.deletarProfissional(idProfissional);
            return ResponseEntity.ok("Profissional deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}

package com.empresa.empresa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empresa.Entities.Empresa;
import com.empresa.empresa.Services.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Rota: GET /empresa/meus-dados
    // Retorna os dados da empresa baseada no Token de quem está logado
    @GetMapping("/meus-dados")
    public ResponseEntity<Empresa> getMeusDados() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailLogado = auth.getName();

        Empresa empresa = empresaService.buscarPorEmailUsuario(emailLogado);
        return ResponseEntity.ok(empresa);
    }

    // Rota: PUT /empresa/atualizar-dados
    @PutMapping("/atualizar-dados")
    public ResponseEntity<Empresa> atualizarDados(@RequestParam(required = false) String nomeEmpresa,
                                                @RequestParam(required = false) String cnpjEmpresa) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailLogado = auth.getName();

        Empresa empresaAtualizada = empresaService.atualizarDadosEmpresa(emailLogado, nomeEmpresa, cnpjEmpresa);
        return ResponseEntity.ok(empresaAtualizada);
    }
}
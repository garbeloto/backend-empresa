package com.empresa.empresa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.empresa.Entities.Empresa;
import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Repositories.EmpresaRepository;
import com.empresa.empresa.Repositories.UsuarioRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Regra para buscar os dados da empresa pelo Login (Token)
    public Empresa buscarPorEmailUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return empresaRepository.findByUsuario(usuario);
    }

    // Parte do antigo "editarUsuario" (Dados da Empresa: Nome, CNPJ)
    @Transactional
    public Empresa atualizarDadosEmpresa(String emailLogado, String nomeEmpresa, String cnpjEmpresa) {
        // 1. Busca a empresa vinculada ao usuário logado
        Empresa empresa = buscarPorEmailUsuario(emailLogado);

        // 2. Atualiza os dados se foram enviados
        if (nomeEmpresa != null && !nomeEmpresa.isBlank()) {
            empresa.setNomeEmpresa(nomeEmpresa);
        }
        
        if (cnpjEmpresa != null && !cnpjEmpresa.isBlank()) {
            empresa.setCnpjEmpresa(cnpjEmpresa);
        }

        return empresaRepository.save(empresa);
    }
}
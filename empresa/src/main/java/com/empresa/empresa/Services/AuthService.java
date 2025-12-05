package com.empresa.empresa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.empresa.Dto.LoginDto;
import com.empresa.empresa.Dto.RegistroEmpresaDto;
import com.empresa.empresa.Dto.TokenResponse;
import com.empresa.empresa.Entities.Empresa;
import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Entities.Enums.UserRole;
import com.empresa.empresa.Repositories.EmpresaRepository;
import com.empresa.empresa.Repositories.UsuarioRepository;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;


    // Método obrigatório do Spring Security para consultar usuário no banco durante o login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    // REGRA DE LOGIN (Antigo /loginUsuario)
    public TokenResponse realizarLogin(LoginDto dados) {
        // O AuthenticationManager faz a validação de senha automaticamente (match)
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha());
        var auth = authenticationManager.authenticate(usernamePassword);

        // Se passou, gera o token
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return new TokenResponse(token);
    }
    // REGRA DE CADASTRO (Antigo /cadastrarComValidacoes)
    @Transactional
    public void registrarEmpresa(RegistroEmpresaDto dados) {
        // 1. Validação de Email Único
        if (usuarioRepository.findByEmail(dados.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }

        // 2. Criptografia da Senha
        String senhaCriptografada = passwordEncoder.encode(dados.getSenha());

        // 3. Criação do Usuário (Login)
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dados.getEmail());
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setRole(UserRole.EMPRESA); // Define que é uma Empresa
        
        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        // 4. Criação da Empresa (Dados de Negócio - CNPJ, Nome)
        Empresa novaEmpresa = new Empresa();
        novaEmpresa.setNomeEmpresa(dados.getNomeEmpresa());
        novaEmpresa.setCnpjEmpresa(dados.getCnpjEmpresa());
        novaEmpresa.setUsuario(usuarioSalvo); // Amarra o login à empresa

        empresaRepository.save(novaEmpresa);
    }
}
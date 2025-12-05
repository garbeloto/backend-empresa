package com.empresa.empresa.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Equivalente ao seu antigo "listarUsuarios"
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Parte do antigo "editarUsuario" (Apenas Email e Senha)
    @Transactional
    public Usuario atualizarCredenciais(Long idUsuario, String novoEmail, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        // Regra: Verificar se o email mudou e se já existe
        if (novoEmail != null && !novoEmail.isBlank() && !novoEmail.equals(usuario.getEmail())) {
            if (usuarioRepository.findByEmail(novoEmail).isPresent()) {
                throw new RuntimeException("Este e-mail já está em uso!");
            }
            usuario.setEmail(novoEmail);
        }

        // Regra: Criptografar nova senha
        if (novaSenha != null && !novaSenha.isBlank()) {
            String senhaCriptografada = passwordEncoder.encode(novaSenha);
            usuario.setSenha(senhaCriptografada);
        }

        return usuarioRepository.save(usuario);
    }

    // Equivalente ao seu antigo "deletar"
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }
}

package com.empresa.empresa.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.empresa.Dto.ProfissionalDto;
import com.empresa.empresa.Entities.Empresa;
import com.empresa.empresa.Entities.Especialidade;
import com.empresa.empresa.Entities.Profissional;
import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Entities.Enums.UserRole;

import com.empresa.empresa.Repositories.EmpresaRepository;
import com.empresa.empresa.Repositories.EspecialidadeRepository;
import com.empresa.empresa.Repositories.ProfissionalRepository;
import com.empresa.empresa.Repositories.UsuarioRepository;

@Service
public class ProfissionalService {
    
    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // --- LISTAGEM ---
    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    public Optional<Profissional> buscarPorNome(String nomeProfissional) {
        return profissionalRepository.findByNomeProfissionalContainingIgnoreCase(nomeProfissional);
    }

    // --- CADASTRO ---
    @Transactional
    public Profissional cadastrarProfissional(ProfissionalDto dados, String emailEmpresaLogada) {
        // 1. Validação: Usa o getEmailColaborador do DTO
        if (usuarioRepository.findByEmail(dados.getEmailProfissional()).isPresent()) {
            throw new RuntimeException("Email já cadastrado no sistema!");
        }

        // 2. Buscar Empresa e Departamento
        Usuario usuarioEmpresa = usuarioRepository.findByEmail(emailEmpresaLogada)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        // Assumindo que você tem um método findByUsuario no EmpresaRepository
        Empresa empresa = empresaRepository.findByUsuario(usuarioEmpresa); 

        Especialidade especialidade = especialidadeRepository.findById(dados.getEspecialidadeId())
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada!"));

        // 3. Criar Usuario (Login) - Pegando email e senha do DTO
        String senhaTexto = (dados.getSenhaProfissional() != null && !dados.getSenhaProfissional().isEmpty()) 
                ? dados.getSenhaProfissional() 
                : "123456"; // Senha padrão se vier vazia
        
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dados.getEmailProfissional());
        novoUsuario.setSenha(passwordEncoder.encode(senhaTexto));
        
        
        novoUsuario.setRole(UserRole.PROFISSIONAL);

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        
        // 4. Criar profissional - Pegando nome do DTO
        Profissional novoProfissional = new Profissional();
        novoProfissional.setNomeProfissional(dados.getNomeProfissional());
        novoProfissional.setStatus(true); // Padrão Ativo
        
        // Vínculos
        novoProfissional.setUsuario(usuarioSalvo);
        novoProfissional.setEmpresa(empresa);
        novoProfissional.setEspecialidade(especialidade);

        return profissionalRepository.save(novoProfissional);
    }

    // --- EDIÇÃO ---
    @Transactional
    public Profissional editarProfissional(Integer idProfissional, ProfissionalDto dados) {
        Profissional profissional = profissionalRepository.findById(idProfissional)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado!"));

        // Atualiza Nome
        profissional.setNomeProfissional(dados.getNomeProfissional());
        
        // Atualiza Status
        if (dados.getStatus() != null) {
            profissional.setStatus(dados.getStatus());
        }

        // Atualiza Departamento
        if (dados.getEspecialidadeId() != null) {
            Especialidade novaEspecialidade = especialidadeRepository.findById(dados.getEspecialidadeId())
                    .orElseThrow(() -> new RuntimeException("Departamento não encontrado!"));
            profissional.setEspecialidade(novaEspecialidade);
        }

        // Atualiza Email (Login)
        // Verifica se o email novo é diferente do atual
        if (!dados.getEmailProfissional().equals(profissional.getUsuario().getUsername())) {
            if (usuarioRepository.findByEmail(dados.getEmailProfissional()).isPresent()) {
                throw new RuntimeException("Email já em uso por outro usuário!");
            }
                profissional.getUsuario().setEmail(dados.getEmailProfissional());
        }
        
        // Atualiza Senha
        if (dados.getSenhaProfissional() != null && !dados.getSenhaProfissional().isBlank()) {
            profissional.getUsuario().setSenha(passwordEncoder.encode(dados.getSenhaProfissional()));
        }

        return profissionalRepository.save(profissional);
    }

    public void deletarProfissional(Integer idProfissional) {
        if (!profissionalRepository.existsById(idProfissional)) {
            throw new RuntimeException("Profissional não encontrado");
        }
        profissionalRepository.deleteById(idProfissional);
    }
}

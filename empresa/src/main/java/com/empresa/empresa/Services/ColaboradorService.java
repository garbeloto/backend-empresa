package com.empresa.empresa.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.empresa.Dto.ColaboradorDto;
import com.empresa.empresa.Entities.Colaborador;
import com.empresa.empresa.Entities.Departamento;
import com.empresa.empresa.Entities.Empresa;
import com.empresa.empresa.Entities.Usuario;
import com.empresa.empresa.Entities.Enums.UserRole;
import com.empresa.empresa.Repositories.ColaboradorRepository;
import com.empresa.empresa.Repositories.DepartamentoRepository;
import com.empresa.empresa.Repositories.EmpresaRepository;
import com.empresa.empresa.Repositories.UsuarioRepository;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // --- LISTAGEM ---
    public List<Colaborador> listarTodos() {
        return colaboradorRepository.findAll();
    }

    public Optional<Colaborador> buscarPorNome(String nome) {
        return colaboradorRepository.findByNomeColaboradorContainingIgnoreCase(nome);
    }

    // --- CADASTRO ---
    @Transactional
    public Colaborador cadastrarColaborador(ColaboradorDto dados, String emailEmpresaLogada) {
        // 1. Validação: Usa o getEmailColaborador do DTO
        if (usuarioRepository.findByEmail(dados.getEmailColaborador()).isPresent()) {
            throw new RuntimeException("Email já cadastrado no sistema!");
        }

        // 2. Buscar Empresa e Departamento
        Usuario usuarioEmpresa = usuarioRepository.findByEmail(emailEmpresaLogada)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        Empresa empresa = empresaRepository.findByUsuario(usuarioEmpresa);

        Departamento departamento = departamentoRepository.findById(dados.getIdDepartamento())
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado!"));

        // 3. Criar Usuario (Login) - Pegando email e senha do DTO
        String senhaTexto = (dados.getSenhaColaborador() != null && !dados.getSenhaColaborador().isEmpty()) 
                ? dados.getSenhaColaborador() 
                : "123456"; // Senha padrão se vier vazia
        
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dados.getEmailColaborador());
        novoUsuario.setSenha(passwordEncoder.encode(senhaTexto));
        novoUsuario.setRole(UserRole.COLABORADOR);

        // 4. Criar Colaborador - Pegando nome do DTO (Sem Cargo)
        Colaborador novoColaborador = new Colaborador();
        novoColaborador.setNomeColaborador(dados.getNomeColaborador());
        novoColaborador.setStatus(true); // Padrão Ativo
        
        // Vínculos
        novoColaborador.setUsuario(novoUsuario);
        novoColaborador.setEmpresa(empresa);
        novoColaborador.setDepartamento(departamento);

        return colaboradorRepository.save(novoColaborador);
    }

    // --- EDIÇÃO ---
    @Transactional
    public Colaborador editarColaborador(Long id, ColaboradorDto dados) {
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado!"));

        // Atualiza Nome
        colaborador.setNomeColaborador(dados.getNomeColaborador());
        
        // Atualiza Status
        if (dados.getStatus() != null) {
            colaborador.setStatus(dados.getStatus());
        }

        // Atualiza Departamento
        if (dados.getIdDepartamento() != null) {
            Departamento novoDepto = departamentoRepository.findById(dados.getIdDepartamento())
                    .orElseThrow(() -> new RuntimeException("Departamento não encontrado!"));
            colaborador.setDepartamento(novoDepto);
        }

        // Atualiza Email (Login)
        // Verifica se o email novo é diferente do atual
        if (!dados.getEmailColaborador().equals(colaborador.getUsuario().getUsername())) {
            if (usuarioRepository.findByEmail(dados.getEmailColaborador()).isPresent()) {
                throw new RuntimeException("Email já em uso por outro usuário!");
            }
                colaborador.getUsuario().setEmail(dados.getEmailColaborador());
        }
        
        // Atualiza Senha (Opcional)
        if (dados.getSenhaColaborador() != null && !dados.getSenhaColaborador().isBlank()) {
            colaborador.getUsuario().setSenha(passwordEncoder.encode(dados.getSenhaColaborador()));
        }

        return colaboradorRepository.save(colaborador);
    }

    public void deletarColaborador(Long id) {
        if (!colaboradorRepository.existsById(id)) {
            throw new RuntimeException("Colaborador não encontrado");
        }
        colaboradorRepository.deleteById(id);
    }
}
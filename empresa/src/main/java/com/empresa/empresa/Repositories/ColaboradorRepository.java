package com.empresa.empresa.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.Colaborador;
import com.empresa.empresa.Entities.Empresa;
import com.empresa.empresa.Entities.Usuario;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    // Buscar por parte do nome (ignorando maiúsculas/minúsculas)
    Optional<Colaborador> findByNomeColaboradorContainingIgnoreCase(String nomeColaborador);

    // Listar colaboradores de uma Empresa específica (para listagem no Dashboard)
    List<Colaborador> findByEmpresa(Empresa empresa);

    // Listar colaboradores de uma Empresa, filtrando pelo Status (Ativo/Inativo)
    // List<Colaborador> findByEmpresaAndStatus(Empresa empresa, Boolean status);

    // Buscar colaborador pelo seu objeto de login
    Colaborador findByUsuarioId(Usuario usuario);
}

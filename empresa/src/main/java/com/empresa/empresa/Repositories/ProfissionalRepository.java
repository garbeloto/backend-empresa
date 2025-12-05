package com.empresa.empresa.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.Empresa;
import com.empresa.empresa.Entities.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{

    // Buscar por parte do nome (ignorando maiúsculas/minúsculas)
    Optional<Profissional> findByNomeContainingIgnoreCase(String nome);

    // Listar profissionais de uma Empresa específica
    List<Profissional> findByEmpresa(Empresa empresa);

    // Listar profissionais de uma Empresa, filtrando pelo Status (Ativo/Inativo)
    //List<Profissional> findByEmpresaAndStatus(Empresa empresa, Boolean status);
    
    // Buscar profissional pelo seu objeto de login
    Profissional findByUsuarioId(Long usuarioId);
    
}

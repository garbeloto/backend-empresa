package com.empresa.empresa.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.Empresa;
import com.empresa.empresa.Entities.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Integer>{

    // Buscar por parte do nome (ignorando maiúsculas/minúsculas)
    Optional<Profissional> findByNomeProfissionalContainingIgnoreCase(String nomeProfissional);

    // Listar profissionais de uma Empresa específica
    List<Profissional> findByEmpresa(Empresa empresa);
    
    // Buscar profissional pelo seu objeto de login
    Profissional findByUsuarioId(Integer usuarioId);
    
}

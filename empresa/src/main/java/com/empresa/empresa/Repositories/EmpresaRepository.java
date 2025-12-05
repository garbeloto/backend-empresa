package com.empresa.empresa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.Empresa;
import com.empresa.empresa.Entities.Usuario;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    // Busca a entidade Empresa pelo seu objeto Usuario (o login do Gestor)
    Empresa findByUsuario(Usuario usuario);
}

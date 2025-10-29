package com.empresa.empresa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}

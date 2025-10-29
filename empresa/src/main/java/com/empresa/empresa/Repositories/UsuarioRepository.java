package com.empresa.empresa.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByEmailUsuario (String emailUsuario);
}

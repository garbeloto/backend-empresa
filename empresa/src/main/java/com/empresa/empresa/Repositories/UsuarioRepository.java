package com.empresa.empresa.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.empresa.empresa.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
      // Método essencial para o Spring Security (o AuthService usa este)
    Optional<Usuario> findByEmail (String email);

    
}

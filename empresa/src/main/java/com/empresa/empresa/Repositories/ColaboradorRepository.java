package com.empresa.empresa.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.Colaborador;
import com.empresa.empresa.Entities.Usuario;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    List<Colaborador> findByNomeColaboradorContainingIgnoreCase(String nomeColaborador); // ContainingIgnoreCase permite
                                                                                         // buscar mesmo se o usuário
                                                                                         // digitar só parte do nome e
                                                                                         // ignora
                                                                                         // maiúsculas/minúsculas.

    Optional<Usuario> findByEmailColaborador(String emailColaborador);
}

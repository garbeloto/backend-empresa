package com.empresa.empresa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer>{
    List<Colaborador> findByNomeColaboradorContainingIgnoreCase(String nomeColaborador); //ContainingIgnoreCase permite buscar mesmo se o usuário digitar só parte do nome e ignora maiúsculas/minúsculas.
}

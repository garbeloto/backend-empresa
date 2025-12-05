package com.empresa.empresa.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    Optional<Especialidade> findByNomeEspecialidadeIgnoreCase(String nomeEspecialidade);
}

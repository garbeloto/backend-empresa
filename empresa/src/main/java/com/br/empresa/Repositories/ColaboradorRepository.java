package com.br.empresa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.empresa.Entities.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer>{ //repositório conecta com banco de dados


}

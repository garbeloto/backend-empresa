package com.empresa.empresa.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.empresa.Entities.CheckIn;
import com.empresa.empresa.Entities.Colaborador;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    // para listar o histótico de check-ins de um colaborador
    List<CheckIn> findByColaborador(Colaborador colaborador);

    
    //busca check-in de um colaborador em uma data específica.
    Optional<CheckIn> findByColaboradorAndDataCheckIns(Colaborador colaborador, LocalDate dataCheckIns);

    //buscar todos os check-ins em um intervalo de datas (para o Dashboard)
    List<CheckIn> findByDataCheckInsBetween(LocalDate inicio, LocalDate fim);
    
    //contar quantos check-ins foram feitos hoje (para taxa de adesão)
    long countByDataCheckIns(LocalDate data);
}



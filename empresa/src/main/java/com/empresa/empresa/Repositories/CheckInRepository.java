package com.empresa.empresa.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.empresa.Entities.CheckIn;
import com.empresa.empresa.Entities.Colaborador;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    // para listar o histótico de check-ins de um colaborador
    List<CheckIn> findByColaborador(Colaborador colaborador);

    /**
     * Busca check-in de um colaborador em uma data específica.
     * Usamos @Query para contornar o erro de parseamento 'No property 'dataCheck'
     * found'.
     * Assumimos que o campo na entidade CheckIn se chama 'dataCheckIn'.
     */
    @Query("SELECT c FROM CheckIn c WHERE c.colaborador = ?1 AND c.dataCheckIn = ?2")
    Optional<CheckIn> findByColaboradorAndDataCheckIn(Colaborador colaborador, LocalDate dataCheckIn);

}

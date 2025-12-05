package com.empresa.empresa.Entities;

import java.time.LocalDate;

import com.empresa.empresa.Entities.Enums.Sentimento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "checkins")
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCheckIn;
    private LocalDate dataCheckIn;

    @Column(length = 500)
    private String descricaoCheckIn;

    @Enumerated(EnumType.STRING)
    private Sentimento sentimento;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    public CheckIn() {
    }

    // Construtor auxiliar que pega a data atual automaticamente
    public CheckIn(Sentimento sentimento, String descricaoCheckIn, Colaborador colaborador) {
        this.dataCheckIn = LocalDate.now(); // Define a data automaticamente
        this.sentimento = sentimento;
        this.descricaoCheckIn = descricaoCheckIn;
        this.colaborador = colaborador;
    }

    public Integer getIdCheckIn() {
        return idCheckIn;
    }

    public void setIdCheckIn(Integer idCheckIn) {
        this.idCheckIn = idCheckIn;
    }

    public LocalDate getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(LocalDate dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public String getDescricaoCheckIn() {
        return descricaoCheckIn;
    }

    public void setDescricaoCheckIn(String descricaoCheckIn) {
        this.descricaoCheckIn = descricaoCheckIn;
    }

    public Sentimento getSentimento() {
        return sentimento;
    }

    public void setSentimento(Sentimento sentimento) {
        this.sentimento = sentimento;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

}

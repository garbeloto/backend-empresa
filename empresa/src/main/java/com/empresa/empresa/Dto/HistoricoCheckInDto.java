package com.empresa.empresa.Dto;

import java.time.LocalDate;

import com.empresa.empresa.Entities.Enums.Sentimento;

public class HistoricoCheckInDto {

    private Integer idCheckIn;
    private LocalDate dataCheckIns;
    private String descricaoCheckIn;
    private Sentimento sentimento;

    public HistoricoCheckInDto() {
    }

    public HistoricoCheckInDto(Integer idCheckIn, LocalDate dataCheckIns, String descricaoCheckIn,
            Sentimento sentimento) {
        this.idCheckIn = idCheckIn;
        this.dataCheckIns = dataCheckIns;
        this.descricaoCheckIn = descricaoCheckIn;
        this.sentimento = sentimento;
    }

    public Integer getIdCheckIn() {
        return idCheckIn;
    }

    public void setIdCheckIn(Integer idCheckIn) {
        this.idCheckIn = idCheckIn;
    }

    public LocalDate getDataCheckIns() {
        return dataCheckIns;
    }

    public void setDataCheckIns(LocalDate dataCheckIns) {
        this.dataCheckIns = dataCheckIns;
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
}

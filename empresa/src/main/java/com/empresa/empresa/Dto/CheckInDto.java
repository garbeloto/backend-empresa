package com.empresa.empresa.Dto;

import com.empresa.empresa.Entities.Enums.Sentimento;

// Usado para registrar um novo Check-in
public class CheckInDto {

    private Sentimento sentimento;
    private String descricaoCheckIn;

    public CheckInDto() {
    }
    
    public CheckInDto(Sentimento sentimento, String descricaoCheckIn) {
        this.sentimento = sentimento;
        this.descricaoCheckIn = descricaoCheckIn;
    }

    public Sentimento getSentimento() {
        return sentimento;
    }

    public void setSentimento(Sentimento sentimento) {
        this.sentimento = sentimento;
    }

    public String getDescricaoCheckIn() {
        return descricaoCheckIn;
    }

    public void setDescricaoCheckIn(String descricaoCheckIn) {
        this.descricaoCheckIn = descricaoCheckIn;
    }

}
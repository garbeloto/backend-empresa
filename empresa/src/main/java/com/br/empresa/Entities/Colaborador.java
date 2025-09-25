package com.br.empresa.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name = "colaborador")
@Entity
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idColaborador;

    @Column(name = "nomeColaborador", length = 200)
    private String nomeColaborador;

    @Column(name = "emailColaborador", unique = true)
    private String emailColaborador;

    @Column(name = "cpfColaborador", unique = true)
    private String cpfColaborador;

    public Colaborador(){

    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public String getEmailColaborador() {
        return emailColaborador;
    }

    public void setEmailColaborador(String emailColaborador) {
        this.emailColaborador = emailColaborador;
    }

    public String getCpfColaborador() {
        return cpfColaborador;
    }

    public void setCpfColaborador(String cpfColaborador) {
        this.cpfColaborador = cpfColaborador;
    }

    
}

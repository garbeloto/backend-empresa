package com.empresa.empresa.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColaborador;
    private String nomeColaborador;
    private String cpfColaborador;

    @Column(unique = true)
    private String emailColaborador;
    private String senhaColaborador;

    @ManyToOne
    private Departamento departamento;
    
    public Colaborador (){

    }

    public Colaborador(String nomeColaborador, String cpfColaborador, String emailColaborador, String senhaColaborador,
            Departamento departamento) {
        this.nomeColaborador = nomeColaborador;
        this.cpfColaborador = cpfColaborador;
        this.emailColaborador = emailColaborador;
        this.senhaColaborador = senhaColaborador;
        this.departamento = departamento;
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

    public String getCpfColaborador() {
        return cpfColaborador;
    }

    public void setCpfColaborador(String cpfColaborador) {
        this.cpfColaborador = cpfColaborador;
    }

    public String getEmailColaborador() {
        return emailColaborador;
    }

    public void setEmailColaborador(String emailColaborador) {
        this.emailColaborador = emailColaborador;
    }

    public String getSenhaColaborador() {
        return senhaColaborador;
    }

    public void setSenhaColaborador(String senhaColaborador) {
        this.senhaColaborador = senhaColaborador;
    }

    public Departamento getDepartamento() {
        return departamento;
    }




    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

  

    
}

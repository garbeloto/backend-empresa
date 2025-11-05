package com.empresa.empresa.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartamento;
    private String nomeDepartamento;

    @OneToMany(mappedBy = "departamento")
    @JsonIgnore
    public List<Colaborador> colaboradores;

    public Departamento(){

    }

    public Departamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }



    public Integer getIdDepartamento() {
        return idDepartamento;
    }



    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }



    public String getNomeDepartamento() {
        return nomeDepartamento;
    }



    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    
}

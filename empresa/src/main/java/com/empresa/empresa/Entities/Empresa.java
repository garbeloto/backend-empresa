package com.empresa.empresa.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;

    private String nomeEmpresa;
    private String cnpjEmpresa;

    // Login da empresa (EMPRESA)
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @OneToMany(mappedBy = "empresa")
    @JsonIgnore 
    private List<Colaborador> colaboradores;


    public Empresa() {
        
    }

    public Empresa(String nomeEmpresa, String cnpjEmpresa, Usuario usuario, List<Colaborador> colaboradores) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.usuario = usuario;
        this.colaboradores = colaboradores;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    



}

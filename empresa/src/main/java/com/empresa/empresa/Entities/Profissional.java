package com.empresa.empresa.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "profissionais")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfissional;

    private String nomeProfissional;
    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade; 
    
    private Boolean status = true; 

    //Login do profissional
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // a qual empresa ele está vinculado (quem o contratou)
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Profissional() {

    }

    public Profissional(String nomeProfissional, Especialidade especialidade, Boolean status, Usuario usuario,
            Empresa empresa) {
        this.nomeProfissional = nomeProfissional;
        this.especialidade = especialidade;
        this.status = status;
        this.usuario = usuario;
        this.empresa = empresa;
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    
    

    }
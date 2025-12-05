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
@Table(name = "colaboradores")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColaborador;
    private String nomeColaborador;

    @ManyToOne
    private Departamento departamento;

    private Boolean status = true; // Valor padrão: Ativo (true)

    // O Login do colaborador (Para ele fazer check-in)
    @OneToOne(cascade = CascadeType.ALL) // Ao salvar colaborador, salva usuario
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // A qual empresa ele pertence?
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Colaborador (){

    }


    public Colaborador(String nomeColaborador, Departamento departamento, Boolean status, Usuario usuario, Empresa empresa) {
        this.nomeColaborador = nomeColaborador;
        this.departamento = departamento;
        this.status = status;
        this.usuario = usuario;
        this.empresa = empresa;
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



    public Departamento getDepartamento() {
        return departamento;
    }



    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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

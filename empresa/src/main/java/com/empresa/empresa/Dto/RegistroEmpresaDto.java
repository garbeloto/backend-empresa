package com.empresa.empresa.Dto;

import jakarta.validation.constraints.Email;

public class RegistroEmpresaDto {

    String nomeEmpresa;
    String cnpjEmpresa;
    @Email(message = "O email deve ser válido.")
    String email; // O email que será o login do usuário EMPRESA
    

    String senha;

    public RegistroEmpresaDto(){

    }

    public RegistroEmpresaDto(String nomeEmpresa, String cnpjEmpresa, String email, String senha) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}

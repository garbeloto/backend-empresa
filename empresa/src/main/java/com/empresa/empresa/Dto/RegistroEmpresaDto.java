package com.empresa.empresa.Dto;



public class RegistroEmpresaDto {

    String nomeEmpresa;
    String cnpjEmpresa;

    String email; // email do usuário admin da empresa
    

    String senha; // senha do usuário admin da empresa

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

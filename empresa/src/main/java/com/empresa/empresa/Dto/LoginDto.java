package com.empresa.empresa.Dto;

import jakarta.validation.constraints.Email;

public class LoginDto {

    @Email(message = "O email deve ser válido.")
    String email;

    String senha;

    public LoginDto() {

    }

    public LoginDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
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

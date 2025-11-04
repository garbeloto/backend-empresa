package com.empresa.empresa.Dto;

public class UsuarioLoginDto {

    private String emailUsuario;
    private String senhaUsuario;

    public UsuarioLoginDto(){

    }

    public UsuarioLoginDto(String emailUsuario, String senhaUsuario) {
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    
}

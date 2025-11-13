package com.empresa.empresa.Dto;

public class ColaboradorLoginDto {

    private String emailColaborador;
    private String senhaColaborador;

    public ColaboradorLoginDto() {

    }

    public ColaboradorLoginDto(String emailColaborador, String senhaColaborador) {
        this.emailColaborador = emailColaborador;
        this.senhaColaborador = senhaColaborador;
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

}

package com.empresa.empresa.Dto;

public class ColaboradorDto {

    private String nomeColaborador;
    private String emailColaborador;
    private String senhaColaborador;
    private int idDepartamento;

    public ColaboradorDto() {

    }

    public ColaboradorDto(String nomeColaborador, String emailColaborador, String senhaColaborador,
            int idDepartamento) {
        this.nomeColaborador = nomeColaborador;
        this.emailColaborador = emailColaborador;
        this.senhaColaborador = senhaColaborador;
        this.idDepartamento = idDepartamento;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
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

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

}

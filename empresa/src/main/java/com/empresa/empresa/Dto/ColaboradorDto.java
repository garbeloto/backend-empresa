package com.empresa.empresa.Dto;
public class ColaboradorDto {

    private String nomeColaborador;
    private String emailColaborador;
    private String senhaColaborador; 
    private Long idDepartamento; 
    private Boolean status;      
    public ColaboradorDto() {
    }

    public ColaboradorDto(String nomeColaborador, String emailColaborador, String senhaColaborador, Long idDepartamento, Boolean status) {
        this.nomeColaborador = nomeColaborador;
        this.emailColaborador = emailColaborador;
        this.senhaColaborador = senhaColaborador;
        this.idDepartamento = idDepartamento;
        this.status = status;
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

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
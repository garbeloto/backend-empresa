package com.empresa.empresa.Dto;


public class ProfissionalDto {

    private String nomeProfissional;
    private String emailProfissional; 
    private String senhaProfissional;
    private Long especialidadeId; 
    private Boolean status; 

    public ProfissionalDto() {

    }

    public ProfissionalDto(String nomeProfissional, String emailProfissional, String senhaProfissional,
            Long especialidadeId, Boolean status) {
        this.nomeProfissional = nomeProfissional;
        this.emailProfissional = emailProfissional;
        this.senhaProfissional = senhaProfissional;
        this.especialidadeId = especialidadeId;
        this.status = status;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getEmailProfissional() {
        return emailProfissional;
    }

    public void setEmailProfissional(String emailProfissional) {
        this.emailProfissional = emailProfissional;
    }

    public String getSenhaProfissional() {
        return senhaProfissional;
    }

    public void setSenhaProfissional(String senhaProfissional) {
        this.senhaProfissional = senhaProfissional;
    }

    public Long getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Long especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    
    
}

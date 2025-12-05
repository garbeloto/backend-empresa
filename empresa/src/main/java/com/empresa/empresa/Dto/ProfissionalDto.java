package com.empresa.empresa.Dto;

import jakarta.validation.constraints.Email;

public class ProfissionalDto {

    String nome;

    @Email(message = "O email deve ser válido.")
    String email; // Email será o login, usado para criar o Usuario PROFISSIONAL
    
    private Long especialidadeId; // Agora recebemos o ID
    
    Boolean status; // Opcional, usado principalmente para edição (true por padrão no Service)

    public ProfissionalDto() {
    }

    public ProfissionalDto(String nome, String email, Long especialidadeId, Boolean status) {
        this.nome = nome;
        this.email = email;
        this.especialidadeId = especialidadeId;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

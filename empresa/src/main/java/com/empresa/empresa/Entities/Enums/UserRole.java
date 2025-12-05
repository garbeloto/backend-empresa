package com.empresa.empresa.Entities.Enums;

public enum UserRole {
    EMPRESA("empresa"), //admin da empresa
    COLABORADOR("colaborador"), //funcionario comum
    PROFISSIONAL("profissional"); //psicologo, coach, etc

    private String role;

    UserRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }


}

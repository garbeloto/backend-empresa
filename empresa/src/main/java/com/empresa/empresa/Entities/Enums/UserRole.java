package com.empresa.empresa.Entities.Enums;

public enum UserRole {
    EMPRESA("empresa"), 
    COLABORADOR("colaborador"), 
    PROFISSIONAL("profissional"); 

    private String role;

    UserRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }


}

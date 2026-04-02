package com.empresa.empresa.Entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.empresa.empresa.Entities.Enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Column(unique = true)
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Usuario() {}

    public Usuario(String email, String senha, UserRole role) {
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    // --- MÉTODOS UserDetails ---

    // Garante que o Spring Security veja a Role com e sem o prefixo ROLE_ para flexibilidade.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.EMPRESA) {
            // Retorna as 2 formas: ROLE_EMPRESA (para hasRole) e EMPRESA (para hasAuthority)
            return List.of(new SimpleGrantedAuthority("ROLE_EMPRESA"), new SimpleGrantedAuthority("EMPRESA"));
        } else if (this.role == UserRole.COLABORADOR) {
            // Retorna as 2 formas: ROLE_COLABORADOR e COLABORADOR
            return List.of(new SimpleGrantedAuthority("ROLE_COLABORADOR"), new SimpleGrantedAuthority("COLABORADOR"));
        } else if (this.role == UserRole.PROFISSIONAL) {
            // Retorna as 2 formas: ROLE_PROFISSIONAL e PROFISSIONAL
            return List.of(new SimpleGrantedAuthority("ROLE_PROFISSIONAL"), new SimpleGrantedAuthority("PROFISSIONAL"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() { 
        return senha; }

    @Override
    public String getUsername() { 
        return email; 
    }


    @Override
    public boolean isAccountNonExpired() {
        return true; 
        }
    @Override
    public boolean isAccountNonLocked() { 
        return true; 
    }
    @Override
    public boolean isCredentialsNonExpired() { 
        return true; 
    }
    @Override
    public boolean isEnabled() { 
        return true; 
    }

    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
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
    public UserRole getRole() { 
        return role; 
    }
    public void setRole(UserRole role) { 
        this.role = role; 
    }
}
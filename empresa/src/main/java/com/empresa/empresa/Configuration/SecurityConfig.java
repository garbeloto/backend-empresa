package com.empresa.empresa.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) 
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
                .authorizeHttpRequests(authorize -> authorize
                        
                        // --- ROTAS PÚBLICAS ---
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register/empresa").permitAll() // Verifique se é /register ou /registrar no seu controller
                        .requestMatchers(HttpMethod.POST, "/auth/registrar/empresa").permitAll()
                        
                        // --- DOCUMENTAÇÃO SWAGGER ---
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        
                        // --- DEPARTAMENTO (Empresa e Profissional podem ver, Empresa cadastra) ---
                        .requestMatchers(HttpMethod.GET, "/departamento/listarDepartamento").hasAnyAuthority("EMPRESA", "ROLE_EMPRESA", "PROFISSIONAL", "ROLE_PROFISSIONAL")
                        .requestMatchers(HttpMethod.POST, "/departamento/**").hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                        
                        // --- COLABORADORES ---
                        .requestMatchers(HttpMethod.POST, "/colaborador/cadastrarColaborador").hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                        .requestMatchers(HttpMethod.PUT, "/colaborador/editarColaborador/**").hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                        .requestMatchers(HttpMethod.DELETE, "/colaborador/deletar/**").hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                        .requestMatchers(HttpMethod.GET, "/colaborador/**").hasAnyAuthority("EMPRESA", "ROLE_EMPRESA", "PROFISSIONAL", "ROLE_PROFISSIONAL")
                        
                        // --- CHECK-IN ---
                        .requestMatchers("/api/checkin/**").hasAnyAuthority("COLABORADOR", "ROLE_COLABORADOR")
                        
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) 
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
/*
 * //para entrar sem senha:
 * 
 * @Bean
 * public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 * return http
 * .csrf(csrf -> csrf.disable())
 * .authorizeHttpRequests(auth -> auth
 * .anyRequest().permitAll())
 * .httpBasic(org.springframework.security.config.Customizer.withDefaults())
 * .build();
 * }
 * }
 */
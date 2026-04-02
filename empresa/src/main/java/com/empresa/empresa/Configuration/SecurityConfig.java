package com.empresa.empresa.Configuration;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private SecurityFilter securityFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                return httpSecurity
                                // 1. ATIVA O CORS NO SPRING SECURITY
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize -> authorize

                                                // --- ROTAS PÚBLICAS ---
                                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/auth/registrar/empresa").permitAll()

                                                
                                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**",
                                                                "/swagger-ui.html")
                                                .permitAll()

                                                // PERMITIR /error
                                                .requestMatchers("/error").permitAll()

                                                // --- REGRAS DE NEGÓCIO ---

                                                // DEPARTAMENTO E ESPECIALIDADES
                                                .requestMatchers(HttpMethod.GET, "/departamento/listarDepartamentos")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                                                .requestMatchers(HttpMethod.GET, "/especialidade/listarEspecialidades")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")

                                                // COLABORADORES
                                                .requestMatchers(HttpMethod.PUT, "/colaborador/editarColaborador/**")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                                                .requestMatchers(HttpMethod.POST, "/colaborador/cadastrarColaborador")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                                                .requestMatchers(HttpMethod.GET, "/colaborador/listarColaboradores")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA", "PROFISSIONAL",
                                                                "ROLE_PROFISSIONAL")
                                                .requestMatchers(HttpMethod.GET, "/colaborador/buscarNomeColaborador")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA", "PROFISSIONAL",
                                                                "ROLE_PROFISSIONAL")
                                                .requestMatchers(HttpMethod.DELETE, "/colaborador/deletar/**")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")

                                                // PROFISSIONAIS
                                                .requestMatchers(HttpMethod.PUT, "/profissional/editarProfissional/**")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                                                .requestMatchers(HttpMethod.POST, "/profissional/cadastrarProfissional")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                                                .requestMatchers(HttpMethod.GET, "/profissional/listarProfissionais")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                                                .requestMatchers(HttpMethod.GET, "/profissional/buscarNomeProfissional")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")
                                                .requestMatchers(HttpMethod.DELETE, "/profissional/deletar/**")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA")

                                                // DASHBOARD
                                                .requestMatchers("/dashboard/**")
                                                .hasAnyAuthority("EMPRESA", "ROLE_EMPRESA", "PROFISSIONAL",
                                                                "ROLE_PROFISSIONAL")

                                                // CHECK-IN
                                                .requestMatchers(HttpMethod.POST, "/checkin/fazerCheckIn")
                                                .hasAnyAuthority("COLABORADOR", "ROLE_COLABORADOR")
                                                .requestMatchers(HttpMethod.GET, "/checkin/historico")
                                                .hasAnyAuthority( "COLABORADOR", "ROLE_COLABORADOR")

                                                .anyRequest().authenticated())
                                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        // 2. CONFIGURAÇÃO DETALHADA DO CORS
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();

                // urls permitidas
                configuration.setAllowedOrigins(Arrays.asList(
                                "http://localhost:8221"

                ));

                // metodos permitidos
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

                // headers permitidos
                configuration.setAllowedHeaders(List.of("*"));

                // permite credenciais se necessário (cookies, auth headers)
                configuration.setAllowCredentials(true);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
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
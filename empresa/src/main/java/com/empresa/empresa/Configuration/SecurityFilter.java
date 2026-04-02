package com.empresa.empresa.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.empresa.empresa.Repositories.UsuarioRepository;
import com.empresa.empresa.Services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetails;
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = recoverToken(request);

        if (token != null) {

            DecodedJWT decodedJWT = tokenService.validateToken(token);

            if (decodedJWT != null) {

                // email do usuário vindo do token
                String login = decodedJWT.getSubject();

                // role vindo do token (ex: COLABORADOR)
                String roleName = decodedJWT.getClaim("role").asString();

                // verifica se o usuário existe
                UserDetails user = usuarioRepository.findByEmail(login).orElse(null);

                if (user != null) {

                    // authorities combinadas do usuário + token
                    Collection<GrantedAuthority> combinedAuthorities = new ArrayList<>();

                    // adiciona a role do token
                    combinedAuthorities.add(new SimpleGrantedAuthority(roleName));
                    combinedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));

                    // mantém as authorities do usuário (se ele tiver)
                    combinedAuthorities.addAll(user.getAuthorities());

                    // autentica no Spring Security
                    UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            combinedAuthorities
                        );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}

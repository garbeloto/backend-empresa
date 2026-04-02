package com.empresa.empresa.Services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.empresa.empresa.Entities.Usuario;
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    // ----------- GERAR TOKEN -----------
    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("api-empresa")
                    .withSubject(usuario.getEmail())
                    .withClaim("role", usuario.getRole().name())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token JWT.", e);
        }
    }


    // ----------- VALIDAR TOKEN -----------
    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("api-empresa")
                    .build()
                    .verify(token);

        } catch (Exception e) {
            
            return null;
        }
    }


    // ----------- EXPIRA EM 2 HORAS -----------
    private Date genExpirationDate() {
        Instant expiration = LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));

        return Date.from(expiration);
    }
}

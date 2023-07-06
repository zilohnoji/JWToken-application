package com.donatoordep.security.configs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.donatoordep.security.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenJWTService {

    @Value("${api.security.token.secret}")
    private String SECRET;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer("auth-api") // Nome ficticio da aplicação
                .withSubject(user.getEmail()) // Login do usuário que está se autenticando
                .withExpiresAt(generateExpirationDate()) // Data de expiração do token
                .sign(algorithm); // Assinatura do token
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
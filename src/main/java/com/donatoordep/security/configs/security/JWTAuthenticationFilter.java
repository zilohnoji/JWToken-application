package com.donatoordep.security.configs.security;

import com.donatoordep.security.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenJWTService service;

    @Autowired
    UserRepository repository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (recoverToken(request) != null) { // Validando e recuperando o token do header
            String login = service.validateToken(recoverToken(request));
            UserDetails user = repository.findByEmail(login); // Buscando o usuario que fez a requisição

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
        }
        filterChain.doFilter(request, response);
    }

    // Recuperar o token JWT do cabeçalho da requisição
    private String recoverToken(HttpServletRequest request) {
        return (request.getHeader("Authorization") != null)
                ? request.getHeader("Authorization").split(" ")[1] : null;
    }
}

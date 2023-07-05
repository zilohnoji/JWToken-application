package com.donatoordep.security.configs.security;

import com.donatoordep.security.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenJWTService service;

    @Autowired
    UserRepository repository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoverToken(request);
        if (token != null) {
            String login = service.validateToken(token);
            UserDetails user = repository.findByEmail(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    // Método para recuperar o token JWT do cabeçalho
    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (!(authHeader == null)) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}

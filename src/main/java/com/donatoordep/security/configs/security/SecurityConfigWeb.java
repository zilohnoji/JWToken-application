package com.donatoordep.security.configs.security;

import com.donatoordep.security.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigWeb {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Desabilitando segurança contra CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        // Declarando o gerenciador de sessão como stateless
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Configurações para sub-diretórios de /auth
        http.securityMatcher("/auth/**")
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/auth/register").permitAll();
                });

        // Configurações para sub-diretórios de /user
        http.securityMatcher("/user/**")
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(HttpMethod.GET, "/user/public").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/user/private").hasRole("ADMIN")
                            .anyRequest().authenticated();
                });

        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

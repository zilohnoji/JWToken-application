package com.donatoordep.security.dto;

import java.time.Instant;

public class TokenAuthenticationSuccesfulDTO {

    private String login;
    private Instant loginMoment = Instant.now();
    private String issuer;
    private String token;

    public TokenAuthenticationSuccesfulDTO(String token, String login, String issuer) {
        this.token = token;
        this.login = login;
        this.issuer = issuer;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getLoginMoment() {
        return loginMoment;
    }
}

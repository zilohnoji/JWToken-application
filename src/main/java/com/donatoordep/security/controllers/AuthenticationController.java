package com.donatoordep.security.controllers;

import com.donatoordep.security.dto.AuthenticationDTO;
import com.donatoordep.security.dto.RoleDTO;
import com.donatoordep.security.dto.TokenAuthenticationSuccesfulDTO;
import com.donatoordep.security.dto.UserDTO;
import com.donatoordep.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    private UserService service;

    @PostMapping(path = "/login")
    public ResponseEntity<TokenAuthenticationSuccesfulDTO> login(@RequestBody AuthenticationDTO dto) {
        return ResponseEntity.ok().body(service.login(dto));
    }

    @PostMapping(path = "/register")
    public void login(@RequestBody UserDTO dto) {
        service.register(dto);
    }
}
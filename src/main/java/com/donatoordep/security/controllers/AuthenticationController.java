package com.donatoordep.security.controllers;

import com.donatoordep.security.configs.security.TokenJWTService;
import com.donatoordep.security.dto.AuthenticationDTO;
import com.donatoordep.security.dto.LoginDTO;
import com.donatoordep.security.dto.UserDTO;
import com.donatoordep.security.entities.User;
import com.donatoordep.security.repositories.UserRepository;
import com.donatoordep.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService service;

    @Autowired
    private TokenJWTService tokenJWTService;

    @PostMapping(path = "/login")
    public ResponseEntity<LoginDTO> login(@RequestBody AuthenticationDTO dto) {
        var auth = manager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        var token = tokenJWTService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok().body(new LoginDTO(token));
    }

    @PostMapping(path = "/register")
    public void login(@RequestBody UserDTO dto) {
        service.register(dto);
    }

}

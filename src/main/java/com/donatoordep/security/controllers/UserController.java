package com.donatoordep.security.controllers;

import com.donatoordep.security.dto.UserDTO;
import com.donatoordep.security.dto.UserMinProjectionDTO;
import com.donatoordep.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public UserDTO insert(@RequestBody UserDTO user) {
        return service.insert(user);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<UserMinProjectionDTO>> findByEmail(
            @RequestParam(name = "email") String email) {
        return ResponseEntity.ok().body(service.findByEmail(email));
    }

}

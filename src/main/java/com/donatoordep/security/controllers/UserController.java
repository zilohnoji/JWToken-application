package com.donatoordep.security.controllers;

import com.donatoordep.security.dto.UserDTO;
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

    @GetMapping(path = "/public")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping(path = "/private/{id}")
    public UserDTO insert(@PathVariable(name = "id")Long id) {
        return service.findById(id);
    }
}

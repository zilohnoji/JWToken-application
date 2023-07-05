package com.donatoordep.security.services;

import com.donatoordep.security.dto.UserDTO;
import com.donatoordep.security.dto.UserMinProjectionDTO;
import com.donatoordep.security.mappers.UserMapper;
import com.donatoordep.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public UserDTO insert(UserDTO dto) {
        return mapper.toDto(mapper.toEntity(dto));
    }

    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(obj -> mapper.toDto(obj)).toList();
    }

    public List<UserMinProjectionDTO> findByEmail(String email) {
        return repository.findUserByEmail(email).stream().map(UserMinProjectionDTO::new).toList();
    }
}

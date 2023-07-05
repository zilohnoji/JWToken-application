package com.donatoordep.security.services;

import com.donatoordep.security.dto.UserDTO;
import com.donatoordep.security.entities.Role;
import com.donatoordep.security.entities.User;
import com.donatoordep.security.mappers.RoleMapper;
import com.donatoordep.security.mappers.UserMapper;
import com.donatoordep.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper mapper;

    public UserDTO insert(UserDTO dto) {
        return mapper.toDto(mapper.toEntity(dto));
    }

    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(obj -> mapper.toDto(obj)).toList();
    }

    public void register(UserDTO dto) {
        String pass = new BCryptPasswordEncoder().encode(dto.getPassword());
        List<Role> roles = dto.getRoles().stream().map(x -> roleMapper.toEntity(x)).toList();
        User user = mapper.toEntity(dto);
        roles.forEach(user::addRole);
        user.setPassword(pass);
        repository.save(user);
    }

    public UserDTO findById(Long id){
        return mapper.toDto(repository.findById(id).orElseThrow());
    }
}

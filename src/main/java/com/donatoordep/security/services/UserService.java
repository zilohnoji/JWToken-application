package com.donatoordep.security.services;

import com.auth0.jwt.JWT;
import com.donatoordep.security.configs.security.TokenJWTService;
import com.donatoordep.security.dto.AuthenticationDTO;
import com.donatoordep.security.dto.TokenAuthenticationSuccesfulDTO;
import com.donatoordep.security.dto.UserDTO;
import com.donatoordep.security.entities.Role;
import com.donatoordep.security.entities.User;
import com.donatoordep.security.mappers.UserMapper;
import com.donatoordep.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private TokenJWTService tokenJWTService;

    @Autowired
    private AuthenticationManager manager;

    // Registro de usuário
    public void register(UserDTO dto) {
        User user = mapper.toEntity(dto);
        dto.getRoles().forEach(roleDTO -> new Role(roleDTO.getId(), roleDTO.getRoleName()));
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        repository.save(user);
    }

    // Autenticação de usuário
    public TokenAuthenticationSuccesfulDTO login(AuthenticationDTO dto) {
        Authentication authenticate = manager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        String token = tokenJWTService.generateToken((User) authenticate.getPrincipal());

        return new TokenAuthenticationSuccesfulDTO(token, authenticate.getName(), JWT.decode(token).getIssuer());
    }

    public UserDTO insert(UserDTO dto) {
        return mapper.toDto(mapper.toEntity(dto));
    }

    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(obj -> mapper.toDto(obj)).toList();
    }

    public UserDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }
}
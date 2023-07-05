package com.donatoordep.security.mappers;

import com.donatoordep.security.dto.UserDTO;
import com.donatoordep.security.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO dto);

    UserDTO toDto(User entity);
}

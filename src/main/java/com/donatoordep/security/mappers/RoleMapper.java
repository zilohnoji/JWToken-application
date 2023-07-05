package com.donatoordep.security.mappers;

import com.donatoordep.security.dto.RoleDTO;
import com.donatoordep.security.dto.UserDTO;
import com.donatoordep.security.entities.Role;
import com.donatoordep.security.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toEntity(RoleDTO dto);

    RoleDTO toDto(Role entity);
}

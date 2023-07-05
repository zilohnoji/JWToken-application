package com.donatoordep.security.dto;

import com.donatoordep.security.enums.RoleName;

public class RoleDTO {

    private Long id;
    private RoleName roleName;

    public RoleDTO(Long id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}

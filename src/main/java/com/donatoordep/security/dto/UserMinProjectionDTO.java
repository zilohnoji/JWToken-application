package com.donatoordep.security.dto;

import com.donatoordep.security.projections.UserMinProjection;

public class UserMinProjectionDTO {

    private String name;
    private String email;
    private String password;
    private String roleName;

    public UserMinProjectionDTO(UserMinProjection projection){
        name = projection.getName();
        email = projection.getEmail();
        password = projection.getPassword();
        roleName = projection.getRoleName();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleName() {
        return roleName;
    }
}

package com.project.erp.model.dto;

import com.project.erp.model.Roles;

import java.util.List;

public class UpdateRoleDto {
    String username;
    List<Roles> roles;

    public UpdateRoleDto(){

    }

    public UpdateRoleDto(String username, List<Roles> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}

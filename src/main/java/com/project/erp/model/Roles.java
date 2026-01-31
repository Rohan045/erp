package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Roles {

    @Id
    private String role;
    @ManyToMany(mappedBy = "rolesList")
    @JsonIgnore
    private List<User> userList;

    public Roles(){

    }

    public Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

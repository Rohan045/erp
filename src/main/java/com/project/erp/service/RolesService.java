package com.project.erp.service;

import com.project.erp.model.Roles;
import com.project.erp.model.User;
import com.project.erp.repository.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesService {

    @Autowired
    private RolesRepo rolesRepo;

    public void addRole(Roles role){
        rolesRepo.save(role);
    }

    public Roles getRole(String r){
        Optional<Roles> role = rolesRepo.findById(r);
        return role.orElse(null);
    }

    public void updateUserList(Roles role, User user){
        role.getUserList().add(user);
        rolesRepo.save(role);
    }
}

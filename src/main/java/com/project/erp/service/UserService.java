package com.project.erp.service;


import com.project.erp.model.Roles;
import com.project.erp.model.User;
import com.project.erp.model.dto.UpdateRoleDto;
import com.project.erp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    RolesService rolesService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public User addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRolesList(new ArrayList<>(List.of(rolesService.getRole("USER"))));//By Default USER Role is given
        return userRepo.save(user);
    }

    public User getUser(String username){
        Optional<User> user = userRepo.findById(username);
        return user.orElse(null);
    }

    public void updateRoles(UpdateRoleDto updateRoleDto) throws Exception{
        User user = getUser(updateRoleDto.getUsername());
        if(user == null){
            throw new Exception("Username does not exist");
        }
        List<Roles> roles = updateRoleDto.getRoles();
        for(Roles r: roles){
            if(rolesService.getRole(r.getRole()) == null){
                throw new Exception("Role provided does not exist - " + r.getRole());
            }
        }
        user.setRolesList(roles);
        userRepo.save(user);
    }
}

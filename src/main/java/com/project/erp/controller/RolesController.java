package com.project.erp.controller;

import com.project.erp.model.Roles;
import com.project.erp.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("roles")
public class RolesController {

    @Autowired
    RolesService rolesService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("post")
    public ResponseEntity<Roles> addRole(@RequestBody Roles role){
        rolesService.addRole(role);
        Roles roleObj = rolesService.getRole(role.getRole());
        if(roleObj != null){
            URI uri = URI.create("/roles/get/" + roleObj.getRole());
            return ResponseEntity.created(uri).body(roleObj);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get/{role}")
    public ResponseEntity<Roles> getRole(@PathVariable("role") String role) throws Exception{
        Roles roleObj = rolesService.getRole(role);
        if(roleObj != null){
            return ResponseEntity.ok(roleObj);
        }
        return ResponseEntity.notFound().build();
    }
}

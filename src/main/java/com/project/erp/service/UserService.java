package com.project.erp.service;


import com.project.erp.model.User;
import com.project.erp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public User addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}

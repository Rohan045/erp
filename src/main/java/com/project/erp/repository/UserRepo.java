package com.project.erp.repository;


import com.project.erp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User findByUsername(String username);

    @Query("""
    SELECT u FROM User u
    JOIN FETCH u.rolesList
    WHERE u.username = :username
    """)
    Optional<User> findByUsernameWithRoles(String username);
}

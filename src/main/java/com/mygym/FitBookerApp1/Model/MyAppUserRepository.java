package com.mygym.FitBookerApp1.Model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAppUserRepository extends JpaRepository<MyAppUser, Long> {
    
    // Find MyAppUser by their username
    Optional<MyAppUser> findByUsername(String username);
}

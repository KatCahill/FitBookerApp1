package com.mygym.FitBookerApp1.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Query method to find a Role by roleName
    Role findByRoleName(String roleName);
}

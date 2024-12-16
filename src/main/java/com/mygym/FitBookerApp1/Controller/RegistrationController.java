package com.mygym.FitBookerApp1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mygym.FitBookerApp1.Model.MyAppUser;
import com.mygym.FitBookerApp1.Model.MyAppUserRepository;
import com.mygym.FitBookerApp1.Model.Role;
import com.mygym.FitBookerApp1.Model.RoleRepository;

@RestController
public class RegistrationController {

    private final MyAppUserRepository myAppUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationController(MyAppUserRepository myAppUserRepository, 
                                  PasswordEncoder passwordEncoder, 
                                  RoleRepository roleRepository) {
        this.myAppUserRepository = myAppUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping(value = "/signup", consumes = "application/json")
    public MyAppUser createUser(@RequestBody MyAppUser user) {
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Fetch the default role from the database
        Role defaultRole = roleRepository.findByRoleName("ROLE_USER");  // Default to USER role
        if (defaultRole == null) {
            throw new RuntimeException("Role ROLE_USER not found.");
        }
        user.setRole(defaultRole);

        // Save the user
        return myAppUserRepository.save(user);
    }
}

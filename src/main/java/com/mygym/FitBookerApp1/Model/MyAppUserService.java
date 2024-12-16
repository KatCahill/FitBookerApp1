package com.mygym.FitBookerApp1.Model;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyAppUserService implements UserDetailsService {

    private final MyAppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<MyAppUser> user = repository.findByUsername(username);

    if (user.isPresent()) {
        MyAppUser userObj = user.get();
        String roleName = userObj.getRoleName();

        if (!roleName.startsWith("ROLE_")) {
            roleName = "ROLE_" + roleName;
        }

        // Log the role being assigned
        System.out.println("Assigned role: " + roleName);

        return User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .authorities(new SimpleGrantedAuthority(roleName)) // This assigns the correct role as authority
                .build();
    } else {
        throw new UsernameNotFoundException(username);
    }
}

    }


package com.mygym.FitBookerApp1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MyAppUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use IDENTITY for auto-incrementing ID
    private Long id;
    
    private String username;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role; // Store roles like "admin" or "user"

    public String getRoleName() {
        return role != null ? role.getRoleName() : null;  // Returns just the role name
    }
}

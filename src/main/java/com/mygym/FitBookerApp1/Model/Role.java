package com.mygym.FitBookerApp1.Model;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;  // Maps to 'role_id' in the database

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;  // Maps to 'role_name' in the database

    // Default constructor
    public Role() {}

    // Getter and Setter for roleName
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // Getter and Setter for roleId
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}

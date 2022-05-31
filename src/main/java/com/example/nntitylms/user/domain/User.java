package com.example.nntitylms.user.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "LMS_USER")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String displayName, String email, String password, Role role) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}

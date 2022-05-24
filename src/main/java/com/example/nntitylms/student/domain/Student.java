package com.example.nntitylms.student.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    public Student() {
    }

    public Student(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
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
}

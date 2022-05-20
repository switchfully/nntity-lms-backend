package com.example.nntitylms.Student.domain;

import java.util.UUID;

public class Student {

    private final UUID id;
    private final String displayName;
    private final String email;
    private final String password;

    public Student(UUID id, String displayName, String email, String password) {
        this.id = id;
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

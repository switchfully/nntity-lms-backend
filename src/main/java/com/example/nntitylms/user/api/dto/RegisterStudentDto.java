package com.example.nntitylms.user.api.dto;

import com.example.nntitylms.user.domain.Role;

public class RegisterStudentDto {

    private final String displayName;
    private final String email;
    private final String password;
    private final Role role = Role.STUDENT;

    public RegisterStudentDto(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
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

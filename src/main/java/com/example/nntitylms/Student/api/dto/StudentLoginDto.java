package com.example.nntitylms.Student.api.dto;

public class StudentLoginDto {

    private final String email;
    private final String password;

    public StudentLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

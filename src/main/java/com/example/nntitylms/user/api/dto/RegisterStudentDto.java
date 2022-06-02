package com.example.nntitylms.user.api.dto;

import com.example.nntitylms.user.domain.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegisterStudentDto {

    @NotBlank(message = "Display name is null or blank")
    private final String displayName;
    @NotNull(message = "Email is null")
    @Email(message = "Email is in a improper format")
    private final String email;
    @NotBlank(message = "Password is null or blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@$%^&(){}:;<>,.?/~_+=|])[A-Za-z0-9!@$%^&(){}:;<>,.?/~_+-=|].{7,}$",
            message = "The Password must be at least 8 characters long and contains at least 1 number, 1 special character, 1 uppercase letter and 1 lowercase letter")
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

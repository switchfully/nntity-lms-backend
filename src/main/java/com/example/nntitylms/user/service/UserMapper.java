package com.example.nntitylms.user.service;

import com.example.nntitylms.user.api.StudentProgressDto;
import com.example.nntitylms.user.api.dto.RegisterStudentDto;
import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserSessionDto toSessionDto(User user, String accessToken) {
        return new UserSessionDto(
                user.getId(),
                user.getDisplayName(),
                accessToken);
    }

    public StudentProgressDto toStudentProgressDto(User student, int completedCodelabs, int totalCodelabs) {
        return new StudentProgressDto(student.getId(), student.getDisplayName(), completedCodelabs, totalCodelabs);
    }

    public User toUser(RegisterStudentDto registerStudentDto) {
        return new User(
                registerStudentDto.getDisplayName(),
                registerStudentDto.getEmail(),
                registerStudentDto.getPassword(),
                registerStudentDto.getRole()
        );
    }
}

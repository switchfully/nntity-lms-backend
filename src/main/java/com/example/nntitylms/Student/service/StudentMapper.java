package com.example.nntitylms.Student.service;

import com.example.nntitylms.Student.api.dto.StudentSessionDto;
import com.example.nntitylms.Student.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentSessionDto toSessionDto(Student foundStudent) {
        return new StudentSessionDto(
                foundStudent.getId(),
                foundStudent.getDisplayName()
        );
    }
}

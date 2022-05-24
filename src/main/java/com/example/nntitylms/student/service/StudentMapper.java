package com.example.nntitylms.student.service;

import com.example.nntitylms.student.api.dto.StudentSessionDto;
import com.example.nntitylms.student.domain.Student;
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

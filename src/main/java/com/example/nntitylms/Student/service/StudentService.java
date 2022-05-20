package com.example.nntitylms.Student.service;

import com.example.nntitylms.Student.api.dto.StudentSessionDto;
import com.example.nntitylms.Student.domain.Student;
import com.example.nntitylms.Student.domain.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentSessionDto loginStudent(String email, String password) {
        Student foundStudent = studentRepository.findByEmail(email);
        return studentMapper.toSessionDto(foundStudent);
    }
}

package com.example.nntitylms.Student.service;

import com.example.nntitylms.Student.api.dto.StudentSessionDto;
import com.example.nntitylms.Student.domain.Student;
import com.example.nntitylms.Student.domain.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentSessionDto loginStudent(String email, String password) {
        checkValidEmailAndPassword(email, password);

        Student foundStudent = studentRepository.findByEmail(email);
        return studentMapper.toSessionDto(foundStudent);
    }

    private void checkValidEmailAndPassword(String email, String password) {
        if (!studentRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
        if (!studentRepository.existsByEmailAndPassword(email, password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
    }
}

package com.example.nntitylms.student.service;

import com.example.nntitylms.student.api.dto.StudentSessionDto;
import com.example.nntitylms.student.domain.Student;
import com.example.nntitylms.student.domain.StudentRepository;
import com.example.nntitylms.Security.KeycloakTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final KeycloakTokenProvider keycloakTokenProvider;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, KeycloakTokenProvider keycloakCall) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.keycloakTokenProvider = keycloakCall;
    }

    public StudentSessionDto loginStudent(String email, String password) {
        checkValidEmailAndPassword(email, password);

        Student foundStudent = studentRepository.findByEmail(email);

        String studentToken = keycloakTokenProvider.getToken(foundStudent.getDisplayName(), foundStudent.getPassword());

        return studentMapper.toSessionDto(foundStudent, studentToken);
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

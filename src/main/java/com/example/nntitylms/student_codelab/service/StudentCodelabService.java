package com.example.nntitylms.student_codelab.service;

import com.example.nntitylms.student.domain.Student;
import com.example.nntitylms.student.domain.StudentRepository;
import com.example.nntitylms.student_codelab.api.dto.StudentCodelabDto;
import com.example.nntitylms.student_codelab.domain.StudentCodelab;
import com.example.nntitylms.student_codelab.domain.StudentCodelabRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentCodelabService {

    private final StudentCodelabMapper studentCodelabMapper;
    private final StudentCodelabRepository studentCodelabRepository;
    private final StudentRepository studentRepository;

    public StudentCodelabService(StudentCodelabMapper studentCodelabMapper, StudentCodelabRepository studentCodelabRepository, StudentRepository studentRepository) {
        this.studentCodelabMapper = studentCodelabMapper;
        this.studentCodelabRepository = studentCodelabRepository;
        this.studentRepository = studentRepository;
    }

    public List<StudentCodelabDto> getCodelabsOfStudent(UUID studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No student found for id " + studentId);
        }
        Student foundStudent = studentRepository.findById(studentId).get();
        List<StudentCodelab> foundCodelabs = studentCodelabRepository.findByStudent(foundStudent);
        return studentCodelabMapper.toDto(foundCodelabs);
    }
}

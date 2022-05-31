package com.example.nntitylms.student_codelab.service;

import com.example.nntitylms.user.domain.User;
import com.example.nntitylms.user.domain.UserRepository;
import com.example.nntitylms.student_codelab.api.dto.StudentCodelabDto;
import com.example.nntitylms.student_codelab.domain.StudentCodelab;
import com.example.nntitylms.student_codelab.domain.StudentCodelabRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentCodelabService {

    private final StudentCodelabMapper studentCodelabMapper;
    private final StudentCodelabRepository studentCodelabRepository;
    private final UserRepository userRepository;

    public StudentCodelabService(StudentCodelabMapper studentCodelabMapper, StudentCodelabRepository studentCodelabRepository, UserRepository userRepository) {
        this.studentCodelabMapper = studentCodelabMapper;
        this.studentCodelabRepository = studentCodelabRepository;
        this.userRepository = userRepository;
    }

    public List<StudentCodelabDto> getCodelabsOfStudent(UUID studentId) {
        if (!userRepository.existsById(studentId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No student found for id " + studentId);
        }
        User foundStudent = userRepository.findById(studentId).get();
        List<StudentCodelab> foundCodelabs = studentCodelabRepository.findByUser(foundStudent);
        return studentCodelabMapper.toDto(foundCodelabs);
    }
}

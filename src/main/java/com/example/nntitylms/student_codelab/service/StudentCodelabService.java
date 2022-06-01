package com.example.nntitylms.student_codelab.service;

import com.example.nntitylms.user.domain.User;
import com.example.nntitylms.user.domain.UserRepository;
import com.example.nntitylms.student_codelab.api.dto.StudentCodelabDto;
import com.example.nntitylms.student_codelab.domain.StudentCodelab;
import com.example.nntitylms.student_codelab.domain.StudentCodelabRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentCodelabService {

    private final StudentCodelabMapper studentCodelabMapper;
    private final StudentCodelabRepository studentCodelabRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentCodelabService.class);

    public StudentCodelabService(StudentCodelabMapper studentCodelabMapper, StudentCodelabRepository studentCodelabRepository, UserRepository userRepository) {
        this.studentCodelabMapper = studentCodelabMapper;
        this.studentCodelabRepository = studentCodelabRepository;
        this.userRepository = userRepository;
    }

    public List<StudentCodelabDto> getCodelabsOfStudent(UUID studentId) {
        List<StudentCodelab> foundCodelabs = findCodelabsInRepository(studentId);
        return studentCodelabMapper.toDto(foundCodelabs);
    }

    public List<StudentCodelabDto> updateStudentCodelabs(UUID studentId, List<StudentCodelabDto> updatedCodelabs) {
        List<StudentCodelab> studentCodelabsToUpdate = findCodelabsInRepository(studentId);
        for (int index = 0; index < studentCodelabsToUpdate.size(); index++) {
            studentCodelabsToUpdate.get(index).setStatus(updatedCodelabs.get(index).getStatus());
        }
        studentCodelabRepository.saveAll(studentCodelabsToUpdate);
        return studentCodelabMapper.toDto(studentCodelabsToUpdate);
    }

    private List<StudentCodelab> findCodelabsInRepository(UUID studentId) {
        if (!userRepository.existsById(studentId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No student found for id " + studentId);
        }
        User foundStudent = userRepository.findById(studentId).get();
        return studentCodelabRepository.findByUser(foundStudent);
    }
}

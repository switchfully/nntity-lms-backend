package com.example.nntitylms.student_codelab.service;

import com.example.nntitylms.student_codelab.api.dto.StudentCodelabDto;
import com.example.nntitylms.student_codelab.domain.StudentCodelab;
import com.example.nntitylms.student_codelab.domain.StudentCodelabRepository;
import com.example.nntitylms.user.domain.User;
import com.example.nntitylms.user.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
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
        Collections.sort(updatedCodelabs);
        List<StudentCodelab> studentCodelabsToUpdate = findCodelabsInRepository(studentId);
        for (StudentCodelabDto studentCodelabToUpdate : updatedCodelabs) {
            StudentCodelab codelab = studentCodelabsToUpdate.stream()
                    .filter((studentCodelab -> studentCodelabToUpdate.getId().equals(studentCodelab.getId())))
                    .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "unexpected error: no studentCodelab found for id " + studentCodelabToUpdate.getId()));
            codelab.setStatus(studentCodelabToUpdate.getStatus());
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

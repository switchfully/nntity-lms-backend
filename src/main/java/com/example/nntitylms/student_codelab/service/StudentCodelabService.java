package com.example.nntitylms.student_codelab.service;

import com.example.nntitylms.codelab.domain.Codelab;
import com.example.nntitylms.codelab.domain.CodelabRepository;
import com.example.nntitylms.course.domain.Course;
import com.example.nntitylms.course.domain.CourseRepository;
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
    private final CourseRepository courseRepository;

    public StudentCodelabService(StudentCodelabMapper studentCodelabMapper, StudentCodelabRepository studentCodelabRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.studentCodelabMapper = studentCodelabMapper;
        this.studentCodelabRepository = studentCodelabRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<StudentCodelabDto> getCodelabsOfStudentByCourse(UUID studentId, Long courseId) {
        List<StudentCodelab> foundStudentCodelabs = findCodelabsInRepository(studentId);
        Course foundCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "unexpected error: no course found for id " + courseId));
        List<Codelab> codelabsByCourse = foundCourse.getCodelabList();

        List<StudentCodelab> filteredStudentCodelabs = foundStudentCodelabs.stream().filter(studentCodelab -> codelabsByCourse.contains(studentCodelab.getCodelab())).toList();
        logger.info("Filtered codelabs for studentId " + studentId + " and courseId " + courseId);
        return studentCodelabMapper.toDto(filteredStudentCodelabs);
    }

    public List<StudentCodelabDto> updateStudentCodelabs(UUID studentId, List<StudentCodelabDto> updatedCodelabs) {
        Collections.sort(updatedCodelabs);
        List<StudentCodelab> studentCodelabsToUpdate = findCodelabsInRepository(studentId);
        for (StudentCodelabDto studentCodelabToUpdate : updatedCodelabs) {
            StudentCodelab codelab = studentCodelabsToUpdate.stream()
                    .filter((studentCodelab -> studentCodelabToUpdate.getId().equals(studentCodelab.getId())))
                    .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "unexpected error: no studentCodelab found for id " + studentCodelabToUpdate.getId()));
            codelab.setStatus(studentCodelabToUpdate.getStatus());
            codelab.setComment(studentCodelabToUpdate.getComment());
        }
        studentCodelabRepository.saveAll(studentCodelabsToUpdate);
        logger.info("Updated codelabs (status or comment) for studentId " + studentId);
        return studentCodelabMapper.toDto(studentCodelabsToUpdate);
    }

    private List<StudentCodelab> findCodelabsInRepository(UUID studentId) {
        User foundStudent = userRepository.findById(studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No student found for id " + studentId));
        return studentCodelabRepository.findByUser(foundStudent);
    }
}

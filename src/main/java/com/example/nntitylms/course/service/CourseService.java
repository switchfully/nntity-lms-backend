package com.example.nntitylms.course.service;

import com.example.nntitylms.course.api.dto.CourseProgressDto;
import com.example.nntitylms.course.domain.Course;
import com.example.nntitylms.course.domain.CourseRepository;
import com.example.nntitylms.student_codelab.domain.StudentCodelab;
import com.example.nntitylms.student_codelab.domain.StudentCodelabRepository;
import com.example.nntitylms.user.domain.User;
import com.example.nntitylms.user.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final UserRepository userRepository;
    private final StudentCodelabRepository studentCodelabRepository;
    private final CourseRepository courseRepository;

    public CourseService(UserRepository userRepository, StudentCodelabRepository studentCodelabRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.studentCodelabRepository = studentCodelabRepository;
        this.courseRepository = courseRepository;
    }

    public List<CourseProgressDto> getCourseProgress(UUID studentId) {
        User foundStudent = userRepository.findById(studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "USer with id " + studentId + " not found"));
        List<StudentCodelab> foundStudentCodelabs = studentCodelabRepository.findByUser(foundStudent);
        return null;
//        List<Course> foundCourses = courseRepository.findAll();


//        return List.of(
//                new CourseProgressDto(1L, "Composition", 1, 1),
//                new CourseProgressDto(2L, "Polymorphism", 1, 2)
//        );
    }
}

package com.example.nntitylms.course.service;

import com.example.nntitylms.codelab.domain.Codelab;
import com.example.nntitylms.codelab.domain.CodelabStatus;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final UserRepository userRepository;
    private final StudentCodelabRepository studentCodelabRepository;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(UserRepository userRepository, StudentCodelabRepository studentCodelabRepository, CourseRepository courseRepository, CourseMapper courseMapper) {
        this.userRepository = userRepository;
        this.studentCodelabRepository = studentCodelabRepository;
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseProgressDto> getCourseProgress(UUID studentId) {
        User foundStudent = userRepository.findById(studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No student found for id " + studentId));
        List<StudentCodelab> foundStudentCodelabs = studentCodelabRepository.findByUser(foundStudent);
        List<Course> foundCourses = courseRepository.findAll();
        return generateCourseProgressDtoList(foundStudentCodelabs, foundCourses);
    }

    private List<CourseProgressDto> generateCourseProgressDtoList(List<StudentCodelab> foundStudentCodelabs, List<Course> foundCourses) {
        List<CourseProgressDto> courseProgressDtoList = new ArrayList<>();
        for (Course course : foundCourses) {
            List<Codelab> courseCodelabs = course.getCodelabList();
            List<StudentCodelab> courseStudentCodelabs = foundStudentCodelabs.stream()
                    .filter(studentCodelab -> courseCodelabs.contains(studentCodelab.getCodelab())).toList();
            Long courseCompletedCodelabs = courseStudentCodelabs.stream()
                    .filter(studentCodelab -> studentCodelab.getStatus().equals(CodelabStatus.DONE) || studentCodelab.getStatus().equals(CodelabStatus.FEEDBACK_NEEDED))
                    .count();
            int courseTotalCodelabs = courseStudentCodelabs.size();
            courseProgressDtoList.add(courseMapper.toDto(course, courseCompletedCodelabs, courseTotalCodelabs));
        }
        return courseProgressDtoList;
    }
}

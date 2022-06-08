package com.example.nntitylms.course.service;

import com.example.nntitylms.course.api.dto.CourseProgressDto;
import com.example.nntitylms.course.domain.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseProgressDto toDto(Course course, Long courseCompletedCodelabs, Long courseTotalCodelabs) {
        return new CourseProgressDto(
                course.getId(),
                course.getName(),
                Math.toIntExact(courseCompletedCodelabs),
                Math.toIntExact(courseTotalCodelabs)
        );
    }
}

package com.example.nntitylms.course.api;

import com.example.nntitylms.course.api.dto.CourseProgressDto;
import com.example.nntitylms.course.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(path = "{studentId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CourseProgressDto> getCourseProgress(@PathVariable UUID studentId) {
        return courseService.getCourseProgress(studentId);
    }

}

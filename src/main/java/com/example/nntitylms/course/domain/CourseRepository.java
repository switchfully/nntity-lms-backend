package com.example.nntitylms.course.domain;

import com.example.nntitylms.codelab.domain.Codelab;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseRepository {

    private final Map<Long, Course> courseList = new HashMap<>();

    public CourseRepository() {
        courseList.put(1L, new Course(1L, "Composition", List.of(new Codelab("Codelab01"))));
        courseList.put(2L, new Course(2L, "Polymorphism", List.of(new Codelab("Codelab02"), new Codelab("Codelab03"))));
    }




}

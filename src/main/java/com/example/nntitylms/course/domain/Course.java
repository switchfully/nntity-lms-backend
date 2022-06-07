package com.example.nntitylms.course.domain;

import com.example.nntitylms.codelab.domain.Codelab;

import java.util.List;

public class Course {
    private final Long id;
    private final String name;
    private final List<Codelab> codelabList;

    public Course(Long id, String name, List<Codelab> codelabList) {
        this.id = id;
        this.name = name;
        this.codelabList = codelabList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Codelab> getCodelabList() {
        return codelabList;
    }
}

package com.example.nntitylms.course.domain;

import com.example.nntitylms.codelab.domain.Codelab;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COURSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany
    @JoinColumn(name = "FK_COURSE_ID")
    private List<Codelab> codelabList;

    public Course(String name, List<Codelab> codelabList) {
        this.name = name;
        this.codelabList = codelabList;
    }

    public Course() {
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

package com.example.nntitylms.course.api.dto;

public class CourseProgressDto {
    private final Long id;
    private final String name;
    private final int completedCodelabs;
    private final int totalCodelabs;

    public CourseProgressDto(Long id, String name, int completedCodelabs, int totalCodelabs) {
        this.id = id;
        this.name = name;
        this.completedCodelabs = completedCodelabs;
        this.totalCodelabs = totalCodelabs;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCompletedCodelabs() {
        return completedCodelabs;
    }

    public int getTotalCodelabs() {
        return totalCodelabs;
    }
}

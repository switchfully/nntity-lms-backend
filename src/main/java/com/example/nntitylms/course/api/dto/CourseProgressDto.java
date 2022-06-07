package com.example.nntitylms.course.api.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseProgressDto that = (CourseProgressDto) o;
        return completedCodelabs == that.completedCodelabs && totalCodelabs == that.totalCodelabs && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, completedCodelabs, totalCodelabs);
    }
}

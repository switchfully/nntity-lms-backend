package com.example.nntitylms.student.api.dto;

import java.util.Objects;
import java.util.UUID;

public class StudentSessionDto {
    private final UUID id;
    private final String displayName;

    public StudentSessionDto(UUID id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public UUID getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSessionDto that = (StudentSessionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName);
    }
}

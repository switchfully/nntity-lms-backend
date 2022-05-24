package com.example.nntitylms.Student.api.dto;

import java.util.Objects;
import java.util.UUID;

public class StudentSessionDto {
    private final UUID id;
    private final String displayName;
    private final String accessToken;

    public StudentSessionDto(UUID id, String displayName, String accessToken) {
        this.id = id;
        this.displayName = displayName;
        this.accessToken = accessToken;
    }

    public UUID getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAccessToken() {
        return accessToken;
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

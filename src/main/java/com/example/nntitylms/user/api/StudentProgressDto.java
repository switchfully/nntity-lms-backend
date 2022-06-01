package com.example.nntitylms.user.api;

import java.util.Objects;
import java.util.UUID;

public class StudentProgressDto {
    private final UUID studentId;
    private final String displayName;
    private final int completedCodelabs;
    private final int totalCodelabs;

    public StudentProgressDto(UUID studentId, String displayName, int completedCodelabs, int totalCodelabs) {
        this.studentId = studentId;
        this.displayName = displayName;
        this.completedCodelabs = completedCodelabs;
        this.totalCodelabs = totalCodelabs;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public String getDisplayName() {
        return displayName;
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
        if (!(o instanceof StudentProgressDto)) return false;
        StudentProgressDto that = (StudentProgressDto) o;
        return getCompletedCodelabs() == that.getCompletedCodelabs() && getTotalCodelabs() == that.getTotalCodelabs() && Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getDisplayName(), that.getDisplayName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getDisplayName(), getCompletedCodelabs(), getTotalCodelabs());
    }

    @Override
    public String toString() {
        return "StudentProgressDto{" +
                "studentId=" + studentId +
                ", displayName='" + displayName + '\'' +
                ", completedCodelabs=" + completedCodelabs +
                ", totalCodelabs=" + totalCodelabs +
                '}';
    }
}

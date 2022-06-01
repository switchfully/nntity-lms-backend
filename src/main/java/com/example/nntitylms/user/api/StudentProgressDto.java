package com.example.nntitylms.user.api;

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
}

package com.example.nntitylms.user.api;

import com.example.nntitylms.codelab.domain.CodelabStatus;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class StudentProgressDto implements Comparable<StudentProgressDto> {
    private final UUID studentId;
    private final String displayName;
    private final Map<CodelabStatus, Long> codelabProgressMap;
    private final int totalCodelabs;

    public StudentProgressDto(UUID studentId, String displayName, Map<CodelabStatus, Long> codelabProgressMap, int totalCodelabs) {
        this.studentId = studentId;
        this.displayName = displayName;
        this.codelabProgressMap = codelabProgressMap;
        this.totalCodelabs = totalCodelabs;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Map<CodelabStatus, Long> getCodelabProgressMap() {
        return codelabProgressMap;
    }

    public int getTotalCodelabs() {
        return totalCodelabs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProgressDto that = (StudentProgressDto) o;
        return totalCodelabs == that.totalCodelabs && Objects.equals(studentId, that.studentId) && Objects.equals(displayName, that.displayName) && Objects.equals(codelabProgressMap, that.codelabProgressMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, displayName, codelabProgressMap, totalCodelabs);
    }

    @Override
    public int compareTo(StudentProgressDto o) {
        return this.displayName.compareTo(o.displayName);
    }
}

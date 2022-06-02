package com.example.nntitylms.student_codelab.api.dto;

import com.example.nntitylms.codelab.domain.CodelabStatus;

import java.util.Objects;
import java.util.UUID;

public class StudentCodelabDto implements Comparable<StudentCodelabDto> {
    private final Long id;
    private final UUID studentId;
    private final String codelabName;
    private final CodelabStatus status;

    public StudentCodelabDto(Long id, UUID studentId, String codelabName, CodelabStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.codelabName = codelabName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public String getCodelabName() {
        return codelabName;
    }

    public CodelabStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCodelabDto that = (StudentCodelabDto) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(codelabName, that.codelabName) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, codelabName, status);
    }

    @Override
    public String toString() {
        return "StudentCodelabDto{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", codelabId=" + codelabName +
                ", status=" + status +
                '}';
    }

    @Override
    public int compareTo(StudentCodelabDto o) {
        return (int) (this.id - o.id);
    }
}

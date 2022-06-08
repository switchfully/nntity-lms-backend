package com.example.nntitylms.student_codelab.api.dto;

import com.example.nntitylms.codelab.domain.CodelabStatus;

import java.util.Objects;
import java.util.UUID;

public class StudentCodelabDto implements Comparable<StudentCodelabDto> {
    private final Long id;
    private final UUID studentId;
    private final String codelabName;
    private final CodelabStatus status;
    private final String comment;

    public StudentCodelabDto(Long id, UUID studentId, String codelabName, CodelabStatus status, String codelabComment) {
        this.id = id;
        this.studentId = studentId;
        this.codelabName = codelabName;
        this.status = status;
        this.comment = codelabComment;
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

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCodelabDto)) return false;
        StudentCodelabDto that = (StudentCodelabDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getCodelabName(), that.getCodelabName()) && getStatus() == that.getStatus() && Objects.equals(getComment(), that.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudentId(), getCodelabName(), getStatus(), getComment());
    }

    @Override
    public String toString() {
        return "StudentCodelabDto{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", codelabName='" + codelabName + '\'' +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public int compareTo(StudentCodelabDto o) {
        return (int) (this.id - o.id);
    }
}

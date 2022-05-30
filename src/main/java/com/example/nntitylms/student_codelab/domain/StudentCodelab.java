package com.example.nntitylms.student_codelab.domain;

import com.example.nntitylms.codelab.domain.Codelab;
import com.example.nntitylms.codelab.domain.CodelabStatus;
import com.example.nntitylms.user.domain.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "STUDENT_CODELAB")
public class StudentCodelab {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_codelab_seq")
    @SequenceGenerator(name = "student_codelab_seq", sequenceName = "student_codelab_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_STUDENT_ID")
    private User student;

    @ManyToOne
    @JoinColumn(name = "FK_CODELAB_ID")
    private Codelab codelab;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROGRESS")
    private CodelabStatus status;

    public StudentCodelab() {
    }

    public StudentCodelab(User student, Codelab codelab, CodelabStatus status) {
        this.student = student;
        this.codelab = codelab;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public User getStudent() {
        return student;
    }

    public Codelab getCodelab() {
        return codelab;
    }

    public CodelabStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCodelab that = (StudentCodelab) o;
        return Objects.equals(student, that.student) && Objects.equals(codelab, that.codelab) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, codelab, status);
    }
}

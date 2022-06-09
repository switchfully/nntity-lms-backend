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
    private User user;

    @ManyToOne
    @JoinColumn(name = "FK_CODELAB_ID")
    private Codelab codelab;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROGRESS")
    private CodelabStatus status;

    @Column(name = "COMMENT")
    private String comment;

    public StudentCodelab() {
    }

    public StudentCodelab(User user, Codelab codelab, CodelabStatus status, String comment) {
        this.user = user;
        this.codelab = codelab;
        this.status = status;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Codelab getCodelab() {
        return codelab;
    }

    public CodelabStatus getStatus() {
        return status;
    }

    public void setStatus(CodelabStatus status) {
        this.status = status;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCodelab)) return false;
        StudentCodelab that = (StudentCodelab) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getCodelab(), that.getCodelab()) && getStatus() == that.getStatus() && Objects.equals(getComment(), that.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getCodelab(), getStatus(), getComment());
    }

    @Override
    public String toString() {
        return "StudentCodelab{" +
                "id=" + id +
                ", user=" + user +
                ", codelab=" + codelab +
                ", status=" + status +
                '}';
    }
}

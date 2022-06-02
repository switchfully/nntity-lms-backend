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

    public StudentCodelab() {
    }

    public StudentCodelab(User user, Codelab codelab, CodelabStatus status) {
        this.user = user;
        this.codelab = codelab;
        this.status = status;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCodelab that = (StudentCodelab) o;
        return Objects.equals(user, that.user) && Objects.equals(codelab, that.codelab) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, codelab, status);
    }

}

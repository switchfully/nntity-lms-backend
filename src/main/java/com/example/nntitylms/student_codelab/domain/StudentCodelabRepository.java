package com.example.nntitylms.student_codelab.domain;

import com.example.nntitylms.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentCodelabRepository extends JpaRepository<StudentCodelab, Long> {

    List<StudentCodelab> findByStudent(Student student);
}

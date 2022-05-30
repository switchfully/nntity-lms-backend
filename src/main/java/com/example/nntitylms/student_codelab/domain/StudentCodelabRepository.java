package com.example.nntitylms.student_codelab.domain;

import com.example.nntitylms.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCodelabRepository extends JpaRepository<StudentCodelab, Long> {

    List<StudentCodelab> findByStudent(User student);
}

package com.example.nntitylms.Student.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    boolean existsByEmail(String email);

    Student findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);
}

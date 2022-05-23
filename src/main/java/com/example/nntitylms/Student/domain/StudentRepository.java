package com.example.nntitylms.Student.domain;

import com.example.nntitylms.Student.api.dto.StudentSessionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    boolean existsByEmail(String email);

    Student findByEmail(String email);

}

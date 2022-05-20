package com.example.nntitylms.Student.domain;

import com.example.nntitylms.Student.api.dto.StudentSessionDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    private final Map<UUID, Student> studentList;

    public StudentRepository() {
        this.studentList = new HashMap<>();
        studentList.put(
                UUID.fromString("ce330ca0-d83a-11ec-9d64-0242ac120002"),
                new Student(UUID.fromString("ce330ca0-d83a-11ec-9d64-0242ac120002"), "Tarzan", "Tarzan@Jungle.com", "JaneIsTheLoveOfMyLife"));
    }

    public Student findStudentByEmail(String email) {
        return studentList.values().stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "no student for e-mail " + email));
    }
}

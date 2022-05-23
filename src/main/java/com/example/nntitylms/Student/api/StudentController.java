package com.example.nntitylms.Student.api;

import com.example.nntitylms.Student.api.dto.StudentSessionDto;
import com.example.nntitylms.Student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(params = {"email", "password"}, produces = APPLICATION_JSON_VALUE)
    StudentSessionDto loginStudent(@RequestParam String email, @RequestParam String password) {
        return studentService.loginStudent(email, password);
    }

}

package com.example.nntitylms.student.api;

import com.example.nntitylms.student.api.dto.StudentSessionDto;
import com.example.nntitylms.student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/students")
@CrossOrigin(origins = {"http://localhost:3000", "https://nntity-lms.netlify.app/"})
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

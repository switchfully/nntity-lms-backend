package com.example.nntitylms.student_codelab.api;

import com.example.nntitylms.student_codelab.api.dto.StudentCodelabDto;
import com.example.nntitylms.student_codelab.service.StudentCodelabService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("student-codelabs")
public class StudentCodelabController {

    private final StudentCodelabService studentCodelabService;

    public StudentCodelabController(StudentCodelabService studentCodelabService) {
        this.studentCodelabService = studentCodelabService;
    }

    @GetMapping(path = "/{studentId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentCodelabDto> getCodelabsOfStudent(@PathVariable UUID studentId) {
        return studentCodelabService.getCodelabsOfStudent(studentId);
    }
}

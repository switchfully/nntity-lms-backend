package com.example.nntitylms.student_codelab.api;

import com.example.nntitylms.student_codelab.api.dto.StudentCodelabDto;
import com.example.nntitylms.student_codelab.service.StudentCodelabService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("student-codelabs")
@CrossOrigin(origins = {"http://localhost:3000", "https://nntity-lms.netlify.app/"})
public class StudentCodelabController {

    private final StudentCodelabService studentCodelabService;

    public StudentCodelabController(StudentCodelabService studentCodelabService) {
        this.studentCodelabService = studentCodelabService;
    }

    @GetMapping(path = "/{studentId}/courses/{courseId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('VIEW_PERSONAL_OVERVIEW')")
    public List<StudentCodelabDto> getCodelabsOfStudentByCourse(@PathVariable UUID studentId, @PathVariable Long courseId) {
        return studentCodelabService.getCodelabsOfStudentByCourse(studentId, courseId);
    }

    @PutMapping(path = "/{studentId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentCodelabDto> updateStudentCodelabs(@PathVariable UUID studentId, @RequestBody List<StudentCodelabDto> updatedCodelabs) {
        return studentCodelabService.updateStudentCodelabs(studentId, updatedCodelabs);
    }
}

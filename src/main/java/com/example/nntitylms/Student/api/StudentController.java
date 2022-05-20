package com.example.nntitylms.Student.api;

import com.example.nntitylms.Student.api.dto.StudentSessionDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(path = "/students")
@CrossOrigin(origins="http://localhost:3000")
public class StudentController {

    @GetMapping(params = {"email", "password"}, produces = APPLICATION_JSON_VALUE)
    StudentSessionDto loginStudent(@RequestParam String email, @RequestParam String password){
        return new StudentSessionDto(UUID.fromString("ce330ca0-d83a-11ec-9d64-0242ac120002"), "Tarzan");
    }

}

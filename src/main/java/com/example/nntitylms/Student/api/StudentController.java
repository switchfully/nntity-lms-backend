package com.example.nntitylms.Student.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(path = "/students")
@CrossOrigin(origins="http://localhost:3000")
public class StudentController {

    @GetMapping(params = {"email", "password"}, produces = APPLICATION_JSON_VALUE)
    String loginStudent(@RequestParam String email, @RequestParam String password){
        return "hello";
    }

}

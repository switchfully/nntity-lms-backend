package com.example.nntitylms.Student.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

    @GetMapping(params = {"email", "password"}, produces = APPLICATION_JSON_VALUE)
    String loginStudent(@RequestParam String email, @RequestParam String password){
        return "hello";
    }

}

package com.example.nntitylms.user.api;

import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/students")
@CrossOrigin(origins = {"http://localhost:3000", "https://nntity-lms.netlify.app/"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(params = {"email", "password"}, produces = APPLICATION_JSON_VALUE)
    UserSessionDto loginStudent(@RequestParam String email, @RequestParam String password) {
        return userService.loginStudent(email, password);
    }

}

package com.example.nntitylms.user.api;

import com.example.nntitylms.user.api.dto.LoginUserDto;
import com.example.nntitylms.user.api.dto.RegisterStudentDto;
import com.example.nntitylms.user.api.dto.UserIdDto;
import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://nntity-lms.netlify.app/"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserSessionDto loginUser(@RequestBody LoginUserDto loginUserDto) {
        return userService.loginUser(loginUserDto);
    }

    @GetMapping(path = "/progression-overview", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('VIEW_STUDENTS_OVERVIEW')")
    List<StudentProgressDto> getStudentsProgress() {
        return userService.getStudentsProgress();
    }

    @PostMapping(path = "/students", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UserIdDto registerStudent(@RequestBody @Valid RegisterStudentDto registerStudentDto) {
        return userService.registerStudent(registerStudentDto);
    }
}

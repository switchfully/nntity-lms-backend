package com.example.nntitylms.user.service;

import com.example.nntitylms.codelab.domain.CodelabStatus;
import com.example.nntitylms.security.KeycloakTokenProvider;
import com.example.nntitylms.student_codelab.domain.StudentCodelab;
import com.example.nntitylms.student_codelab.domain.StudentCodelabRepository;
import com.example.nntitylms.user.api.StudentProgressDto;
import com.example.nntitylms.user.api.dto.LoginUserDto;
import com.example.nntitylms.user.api.dto.RegisterStudentDto;
import com.example.nntitylms.user.api.dto.UserIdDto;
import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.domain.Role;
import com.example.nntitylms.user.domain.User;
import com.example.nntitylms.user.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeycloakTokenProvider keycloakTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final StudentCodelabRepository studentCodelabRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, KeycloakTokenProvider keycloakCall, StudentCodelabRepository studentCodelabRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.keycloakTokenProvider = keycloakCall;
        this.studentCodelabRepository = studentCodelabRepository;
    }

    public UserSessionDto loginUser(LoginUserDto loginUserDto) {
        checkValidEmailAndPassword(loginUserDto.getEmail(), loginUserDto.getPassword());

        User foundUser = userRepository.findByEmail(loginUserDto.getEmail());
        String userToken = keycloakTokenProvider.getToken(foundUser.getDisplayName(), foundUser.getPassword());
        logger.info(loginUserDto.getEmail() + " successfully logged in");
        return userMapper.toSessionDto(foundUser, userToken);
    }

    public UserIdDto registerStudent(RegisterStudentDto registerStudentDto) {
        User studentToRegister = userMapper.toUser(registerStudentDto);

        CheckUniqueEmail(studentToRegister);
        userRepository.save(studentToRegister);
        logger.info("Student save to the database.");

        return new UserIdDto(studentToRegister.getId());
    }

    private void checkValidEmailAndPassword(String email, String password) {
        if (!userRepository.existsByEmail(email)) {
            logger.error("Email Address does not exist");
            throw new ResponseStatusException(BAD_REQUEST, "Invalid credentials");
        }
        if (!userRepository.existsByEmailAndPassword(email, password)) {
            logger.error("email and password combination does not exist");
            throw new ResponseStatusException(BAD_REQUEST, "Invalid credentials");
        }
    }

    private void CheckUniqueEmail(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.error("An account already exist with this email address!");
            throw new ResponseStatusException(BAD_REQUEST, "An account already exist with this email address!");
        }
    }

    public List<StudentProgressDto> getStudentsProgress() {
        List<User> studentList = userRepository.findByRole(Role.STUDENT);
        return generateProgressDtoList(studentList);
    }

    private List<StudentProgressDto> generateProgressDtoList(List<User> studentList) {
        List<StudentProgressDto> studentProgressDtoList = new ArrayList<>();
        for (User student : studentList) {
            List<StudentCodelab> studentCodelabList = studentCodelabRepository.findByUser(student);
            List<StudentCodelab> completedCodelabList = studentCodelabList.stream()
                    .filter(studentCodelab -> studentCodelab.getStatus().equals(CodelabStatus.DONE) || studentCodelab.getStatus().equals(CodelabStatus.FEEDBACK_NEEDED))
                    .toList();
            int completedCodelabs = completedCodelabList.size();
            int totalCodelabs = studentCodelabList.size();
            StudentProgressDto studentProgressDto = userMapper.toStudentProgressDto(student, completedCodelabs, totalCodelabs);
            studentProgressDtoList.add(studentProgressDto);
        }
        Collections.sort(studentProgressDtoList);
        return studentProgressDtoList;
    }
}

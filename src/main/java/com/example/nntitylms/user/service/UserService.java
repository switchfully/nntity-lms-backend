package com.example.nntitylms.user.service;

import com.example.nntitylms.codelab.domain.CodelabStatus;
import com.example.nntitylms.student_codelab.domain.StudentCodelab;
import com.example.nntitylms.student_codelab.domain.StudentCodelabRepository;
import com.example.nntitylms.user.api.StudentProgressDto;
import com.example.nntitylms.user.api.dto.LoginUserDto;
import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.domain.Role;
import com.example.nntitylms.user.domain.User;
import com.example.nntitylms.user.domain.UserRepository;
import com.example.nntitylms.security.KeycloakTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private void checkValidEmailAndPassword(String email, String password) {
        if (!userRepository.existsByEmail(email)) {
            logger.error("Email Address does not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
        if (!userRepository.existsByEmailAndPassword(email, password)) {
            logger.error("email and password combination does not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
    }

    public List<StudentProgressDto> getStudentsProgress() {
        List<User> studentList = userRepository.findByRole(Role.STUDENT);
        List<StudentProgressDto> studentProgressDtoList = new ArrayList<>();

        for (User student : studentList) {
            List<StudentCodelab> studentCodelabList = studentCodelabRepository.findByUser(student);
            List<StudentCodelab> completedCodelabList = studentCodelabList.stream().filter(studentCodelab -> studentCodelab.getStatus().equals(CodelabStatus.DONE)).toList();
            int completedCodelabs = completedCodelabList.size();
            int totalCodelabs = studentCodelabList.size();
            StudentProgressDto studentProgressDto = userMapper.toStudentProgressDto(student, completedCodelabs, totalCodelabs);
            studentProgressDtoList.add(studentProgressDto);
        }

        return studentProgressDtoList;
    }
}

package com.example.nntitylms.user.service;

import com.example.nntitylms.security.KeycloakTokenProvider;
import com.example.nntitylms.user.api.dto.LoginUserDto;
import com.example.nntitylms.user.api.dto.RegisterStudentDto;
import com.example.nntitylms.user.api.dto.UserIdDto;
import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.domain.User;
import com.example.nntitylms.user.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeycloakTokenProvider keycloakTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, UserMapper userMapper, KeycloakTokenProvider keycloakCall) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.keycloakTokenProvider = keycloakCall;
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
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ResponseStatusException(BAD_REQUEST, "An account already exist with this email address!");
        }
    }
}

package com.example.nntitylms.user.service;

import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.domain.User;
import com.example.nntitylms.user.domain.UserRepository;
import com.example.nntitylms.security.KeycloakTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeycloakTokenProvider keycloakTokenProvider;

    public UserService(UserRepository userRepository, UserMapper userMapper, KeycloakTokenProvider keycloakCall) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.keycloakTokenProvider = keycloakCall;
    }

    public UserSessionDto loginStudent(String email, String password) {
        checkValidEmailAndPassword(email, password);

        User foundUser = userRepository.findByEmail(email);

        String userToken = keycloakTokenProvider.getToken(foundUser.getDisplayName(), foundUser.getPassword());

        return userMapper.toSessionDto(foundUser, userToken);
    }

    private void checkValidEmailAndPassword(String email, String password) {
        if (!userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
        if (!userRepository.existsByEmailAndPassword(email, password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
    }
}

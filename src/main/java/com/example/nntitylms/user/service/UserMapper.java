package com.example.nntitylms.user.service;

import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserSessionDto toSessionDto(User user, String accessToken) {
        return new UserSessionDto(
                user.getId(),
                user.getDisplayName(),
                accessToken);
    }
}

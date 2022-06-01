package com.example.nntitylms.user.api.dto;

import java.util.UUID;

public class UserIdDto {
    private UUID id;

    public UserIdDto(UUID id) {
        this.id = id;
    }

    public UserIdDto() {
    }

    public UUID getId() {
        return id;
    }
}

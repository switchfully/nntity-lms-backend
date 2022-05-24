package com.example.nntitylms.codelab.api.dto;

import com.example.nntitylms.codelab.domain.CodelabStatus;

public class CodelabDto {

    private final Long id;
    private final String name;
    private final CodelabStatus status;

    public CodelabDto(Long id, String name, CodelabStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CodelabStatus getStatus() {
        return status;
    }
}

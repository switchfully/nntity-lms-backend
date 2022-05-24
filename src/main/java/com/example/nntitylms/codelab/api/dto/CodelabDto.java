package com.example.nntitylms.codelab.api.dto;

import com.example.nntitylms.codelab.domain.CodelabStatus;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodelabDto that = (CodelabDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }

    @Override
    public String toString() {
        return "CodelabDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

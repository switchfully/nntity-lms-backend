package com.example.nntitylms.codelab.api.dto;

import java.util.Objects;

public class CodelabDto {

    private final Long id;
    private final String name;

    public CodelabDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodelabDto that = (CodelabDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CodelabDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

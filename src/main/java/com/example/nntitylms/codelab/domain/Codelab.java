package com.example.nntitylms.codelab.domain;

import java.util.Objects;

public class Codelab {

    private final Long id;
    private final String name;
    private final CodelabStatus status;

    public Codelab(Long id, String name, CodelabStatus status) {
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
        Codelab codelab = (Codelab) o;
        return Objects.equals(id, codelab.id) && Objects.equals(name, codelab.name) && status == codelab.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }
}

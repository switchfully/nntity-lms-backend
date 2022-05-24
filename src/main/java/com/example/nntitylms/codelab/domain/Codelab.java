package com.example.nntitylms.codelab.domain;

import java.util.Objects;

public class Codelab {

    private final Long id;
    private final String name;

    public Codelab(Long id, String name) {
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
        Codelab codelab = (Codelab) o;
        return Objects.equals(id, codelab.id) && Objects.equals(name, codelab.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

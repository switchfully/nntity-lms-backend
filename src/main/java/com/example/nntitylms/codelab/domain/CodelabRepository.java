package com.example.nntitylms.codelab.domain;

import com.example.nntitylms.codelab.api.dto.CodelabDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CodelabRepository {

    private final Map<Long, Codelab> codelabList = new HashMap<>();

    public CodelabRepository() {
        codelabList.put(1L, new Codelab(1L, "Codelab01"));
        codelabList.put(2L, new Codelab(2L, "Codelab02"));
    }

    public List<Codelab> findAll() {
        return codelabList.values().stream().toList();
    }
}

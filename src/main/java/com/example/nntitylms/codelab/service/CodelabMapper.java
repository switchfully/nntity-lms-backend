package com.example.nntitylms.codelab.service;

import com.example.nntitylms.codelab.api.dto.CodelabDto;
import com.example.nntitylms.codelab.domain.Codelab;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodelabMapper {
    public CodelabDto toDto(Codelab codelab) {
        return new CodelabDto(codelab.getId(), codelab.getName(), codelab.getStatus());
    }

    public List<CodelabDto> toDto(List<Codelab> codelabList) {
        return codelabList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

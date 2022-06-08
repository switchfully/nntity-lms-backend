package com.example.nntitylms.student_codelab.service;

import com.example.nntitylms.student_codelab.api.dto.StudentCodelabDto;
import com.example.nntitylms.student_codelab.domain.StudentCodelab;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentCodelabMapper {

    public StudentCodelabDto toDto(StudentCodelab codelab) {
        return new StudentCodelabDto(
                codelab.getId(),
                codelab.getUser().getId(),
                codelab.getCodelab().getName(),
                codelab.getStatus(),
                codelab.getComment()
        );
    }

    public List<StudentCodelabDto> toDto(List<StudentCodelab> codelabList) {
        return codelabList.stream()
                .map(this::toDto)
                .sorted()
                .collect(Collectors.toList());
    }
}

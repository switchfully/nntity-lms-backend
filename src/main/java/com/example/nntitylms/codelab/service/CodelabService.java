package com.example.nntitylms.codelab.service;

import com.example.nntitylms.codelab.api.dto.CodelabDto;
import com.example.nntitylms.codelab.domain.Codelab;
import com.example.nntitylms.codelab.domain.CodelabRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CodelabService {

    private final CodelabRepository codelabRepository;
    private final CodelabMapper codelabMapper;

    public CodelabService(CodelabRepository codelabRepository, CodelabMapper codelabMapper) {
        this.codelabRepository = codelabRepository;
        this.codelabMapper = codelabMapper;
    }

    public List<CodelabDto> getAll() {
        List<Codelab> foundCodelabs = codelabRepository.findAll();
        return codelabMapper.toDto(foundCodelabs);
    }
}

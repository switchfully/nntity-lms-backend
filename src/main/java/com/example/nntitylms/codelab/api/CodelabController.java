package com.example.nntitylms.codelab.api;

import com.example.nntitylms.codelab.api.dto.CodelabDto;
import com.example.nntitylms.codelab.domain.CodelabStatus;
import com.example.nntitylms.codelab.service.CodelabService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/codelabs")
public class CodelabController {

    private final CodelabService codelabService;

    public CodelabController(CodelabService codelabService) {
        this.codelabService = codelabService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<CodelabDto> getAllCodelabs() {
        return codelabService.getAll();
    }
}

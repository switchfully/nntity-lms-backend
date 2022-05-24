package com.example.nntitylms.codelab.domain;

import com.example.nntitylms.codelab.api.dto.CodelabDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface CodelabRepository extends JpaRepository<Codelab, Long> {

}

package com.example.nntitylms.codelab.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodelabRepository extends JpaRepository<Codelab, Long> {

}

package com.example.nntitylms.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    List<User> findByRole(Role role);
}

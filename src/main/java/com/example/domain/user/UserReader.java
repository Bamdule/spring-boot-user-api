package com.example.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

public interface UserReader {

    Optional<User> findById(Long id);
    User getById(Long id);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    List<User> getUsers(Pageable pageable);

    Long count();

}

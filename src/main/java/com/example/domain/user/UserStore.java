package com.example.domain.user;

public interface UserStore {

    User createUser(String name, String email);

    void deleteUser(Long id);

}

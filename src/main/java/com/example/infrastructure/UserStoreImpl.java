package com.example.infrastructure;

import org.springframework.stereotype.Component;

import com.example.domain.user.User;
import com.example.domain.user.UserReader;
import com.example.domain.user.UserStore;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserStoreImpl implements UserStore {

    private final UserRepository userRepository;
    private final UserReader userReader;

    @Override
    public User createUser(String name, String email) {
        User user = new User(name, email);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userReader.getById(id);
        userRepository.delete(user);
    }
}

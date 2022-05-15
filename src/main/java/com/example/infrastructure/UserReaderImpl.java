package com.example.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.domain.user.User;
import com.example.domain.user.UserReader;
import com.example.exception.BusinessException;
import com.example.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserReaderImpl implements UserReader {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> {
                throw new BusinessException(ErrorCode.USER_NOT_FOUND);
            });
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsers(Pageable pageable) {

        Page<User> page = userRepository.findAll(pageable);
        return page.getContent();
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}

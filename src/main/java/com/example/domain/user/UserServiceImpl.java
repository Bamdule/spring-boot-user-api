package com.example.domain.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.exception.BusinessException;
import com.example.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserStore userStore;
    private final UserReader userReader;

    @Transactional
    @Override
    public UserInfo.Main createUser(UserCommand.Create create) {
        final String name = create.getName();
        final String email = create.getEmail();

        userReader.findByEmail(email)
            .ifPresent(user -> {
                throw new BusinessException(ErrorCode.USER_EMAIL_DUPLICATED);
            });

        userReader.findByName(name)
            .ifPresent(user -> {
                throw new BusinessException(ErrorCode.USER_NAME_DUPLICATED);
            });

        User user = userStore.createUser(name, email);

        return UserInfo.Main.of(user);
    }

    @Transactional
    @Override
    public UserInfo.Main updateUser(Long id, @Valid @RequestBody UserCommand.Update update) {

        userReader.findByName(update.getName())
            .ifPresent(user -> {
                if (!user.getId().equals(id)) {
                    throw new BusinessException(ErrorCode.USER_NAME_DUPLICATED);
                }
            });

        User user = userReader.getById(id);
        user.updateUser(update);

        return UserInfo.Main.of(user);
    }

    @Override
    public void deleteUser(Long id) {
        userStore.deleteUser(id);
    }

    @Override
    public UserInfo.Main getUserById(Long id) {
        User user = userReader.findById(id)
            .orElseThrow(() -> {
                throw new BusinessException(ErrorCode.USER_NOT_FOUND);
            });

        return UserInfo.Main.of(user);
    }

    @Override
    public List<UserInfo.Main> getUsers(Pageable pageable) {

        return userReader.getUsers(pageable)
            .stream()
            .map(UserInfo.Main::of)
            .collect(Collectors.toList());
    }

    @Override
    public Long getTotalCount() {
        return userReader.count();
    }
}

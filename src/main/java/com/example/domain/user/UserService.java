package com.example.domain.user;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface UserService {

    UserInfo.Main createUser(UserCommand.Create create);

    UserInfo.Main updateUser(Long id, UserCommand.Update update);

    void deleteUser(Long id);

    UserInfo.Main getUserById(Long id);

    List<UserInfo.Main> getUsers(Pageable pageable);

    Long getTotalCount();

}

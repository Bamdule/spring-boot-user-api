package com.example.presentaion.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.common.Pages;
import com.example.domain.user.UserCommand;
import com.example.domain.user.UserInfo;
import com.example.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse.User> findUserById(@PathVariable Long id) {

        UserInfo.Main user = userService.getUserById(id);

        return ResponseEntity.ok(UserResponse.User.of(user));
    }

    @GetMapping
    public ResponseEntity<Pages<UserResponse.User>> findUsers(
        @PageableDefault(size = 20) Pageable pageable
    ) {

        List<UserResponse.User> users = userService.getUsers(pageable)
            .stream()
            .map(UserResponse.User::of)
            .collect(Collectors.toList());

        Long count = userService.getTotalCount();

        Pages<UserResponse.User> pages = Pages.<UserResponse.User>builder()
            .contents(users)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .total(count)
            .build();

        return ResponseEntity.ok(pages);
    }

    @PostMapping
    public ResponseEntity<UserResponse.User> createUser(@Valid @RequestBody UserRequest.Create create) {

        UserCommand.Create createCommand = create.toCommand();

        UserInfo.Main user = userService.createUser(createCommand);

        return ResponseEntity.ok(UserResponse.User.of(user));
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<UserResponse.User> updateUser(
        @PathVariable(value = "id") Long id,
        @Valid @RequestBody UserRequest.Update update
    ) {

        UserCommand.Update updateCommand = update.toCommand();

        UserInfo.Main user = userService.updateUser(id, updateCommand);

        return ResponseEntity.ok(UserResponse.User.of(user));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable(value = "id") Long id
    ) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}

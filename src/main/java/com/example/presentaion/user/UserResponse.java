package com.example.presentaion.user;

import java.time.LocalDateTime;

import com.example.domain.user.UserInfo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResponse {

    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class User {
        private Long id;
        private String name;
        private String email;

        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public static User of(UserInfo.Main main) {
            return UserResponse.User.builder()
                .id(main.getId())
                .name(main.getName())
                .email(main.getEmail())
                .createDate(main.getCreateDate())
                .updateDate(main.getUpdateDate())
                .build();
        }

    }
}

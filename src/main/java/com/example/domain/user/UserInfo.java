package com.example.domain.user;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserInfo {

    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class Main {
        private Long id;
        private String name;
        private String email;

        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public static UserInfo.Main of(User user) {
            return UserInfo.Main
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createDate(user.getCreateDate())
                .updateDate(user.getUpdateDate())
                .build();
        }
    }
}

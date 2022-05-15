package com.example.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserCommand {

    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class Create {
        private String name;
        private String email;
    }

    @AllArgsConstructor
    @Getter
    public static class Update {
        private String name;
    }

}

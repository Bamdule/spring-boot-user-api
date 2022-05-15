package com.example.presentaion.user;

import javax.validation.constraints.NotBlank;

import com.example.domain.user.UserCommand;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Setter
    @Getter
    public static class Create {

        @NotBlank(message = "name은 필수 값 입니다.")
        private String name;
        @NotBlank(message = "email은 필수 값 입니다.")
        private String email;

        public UserCommand.Create toCommand() {
            return UserCommand.Create
                .builder()
                .name(this.getName())
                .email(this.getEmail())
                .build();
        }
    }

    @Setter
    @Getter
    public static class Update {

        @NotBlank(message = "name은 필수 값 입니다.")
        private String name;

        public UserCommand.Update toCommand() {
            return new UserCommand.Update(this.name);
        }
    }
}

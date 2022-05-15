package com.example.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NAME_DUPLICATED(HttpStatus.CONFLICT, "중복된 이름입니다."),
    USER_EMAIL_DUPLICATED(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않은 회원입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

package com.services.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USERNAME_IS_TAKEN(HttpStatus.BAD_REQUEST, "User with the specified username already exists."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User with the specified id wasn't found."),
    AMOUNT_OF_POSTS_CHANGE_IS_INCORRECT(HttpStatus.BAD_REQUEST, "Incorrect value for changing the amount of posts.");

    private final HttpStatus httpStatus;

    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}

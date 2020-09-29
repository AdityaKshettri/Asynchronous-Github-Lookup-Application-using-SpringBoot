package com.aditya.project.async.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCatalog {

    APPLICATION_ERROR(1, "Internal Error!!", ErrorLevel.TECHNICAL),
    USER_NOT_FOUND_ERROR(2, "User not found!!", ErrorLevel.FUNCTIONAL),
    USER_ALREADY_EXISTS_ERROR(3, "User already exist!!", ErrorLevel.FUNCTIONAL),
    GITHUB_USER_NOT_FOUND_ERROR(4, "User not found in Github!!", ErrorLevel.FUNCTIONAL),
    GITHUB_API_ERROR(5, "Internal Error!!", ErrorLevel.TECHNICAL);

    private final int code;

    private final String message;

    private final ErrorLevel errorLevel;
}

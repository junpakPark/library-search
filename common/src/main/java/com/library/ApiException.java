package com.library;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final String errorMessage;
    private final ErrorType errorType;
    private final HttpStatus httpStatus;

    public ApiException(final String errorMessage, final ErrorType errorType, final HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.errorType = errorType;
        this.httpStatus = httpStatus;
    }
}

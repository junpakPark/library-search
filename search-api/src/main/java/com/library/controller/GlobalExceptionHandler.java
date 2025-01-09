package com.library.controller;

import com.library.ApiException;
import com.library.ErrorType;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(final ApiException e) {
        log.error("Api Exception occurred: message={}, className={}", e.getErrorMessage(), e.getClass().getName());
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorType(), e.getErrorMessage()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(final BindException e) {
        log.error("Bind Exception occurred: message={}, className={}", e.getMessage(), e.getClass().getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ErrorType.INVALID_PARAMETER, createMessage(e)));
    }

    private String createMessage(final BindException e) {
        if (Objects.nonNull(e.getFieldError()) && Objects.nonNull(e.getFieldError().getDefaultMessage())) {
            return e.getFieldError().getDefaultMessage();
        }
        return e.getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(",")) + " 값들이 정확하지 않습니다.";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("Exception occurred: message={}, className={}", e.getMessage(), e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorType.UNKNOWN, ErrorType.UNKNOWN.getDescription()));
    }

}

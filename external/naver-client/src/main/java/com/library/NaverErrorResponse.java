package com.library;

public record NaverErrorResponse(
        String errorMessage,
        String errorCode
) {
}

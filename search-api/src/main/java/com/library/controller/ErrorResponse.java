package com.library.controller;

import com.library.ErrorType;

public record ErrorResponse(
        ErrorType errorType,
        String errorMessage
) {
}

package com.library;

import lombok.Getter;

@Getter
public enum ErrorType {
    EXTERNAL_API("외부 API 호출 에러"),
    INVALID_PARAMETER("잘못된 요청 값"),
    UNKNOWN("알 수 없는 에러"),
    ;

    private final String description;

    ErrorType(final String description) {
        this.description = description;
    }
}

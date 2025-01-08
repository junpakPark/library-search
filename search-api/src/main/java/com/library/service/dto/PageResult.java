package com.library.service.dto;

import java.util.List;

public record PageResult<T>(
        int size,
        int page,
        int totalElements,
        List<T> contents
) {
}

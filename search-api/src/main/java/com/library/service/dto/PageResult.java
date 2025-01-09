package com.library.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "페이징 결과")
public record PageResult<T>(
        @Schema(description = "페이지 크기", example = "10")
        int size,
        @Schema(description = "현재 페이지번호", example = "1")
        int page,
        @Schema(description = "전체 요소수", example = "100")
        int totalElements,
        @Schema(description = "본문")
        List<T> contents
) {
}

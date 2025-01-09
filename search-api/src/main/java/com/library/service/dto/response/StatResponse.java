package com.library.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "검색통계")
public record StatResponse(
        @Schema(description = "쿼리", example = "HTTP")
        String query,
        @Schema(description = "검색횟수", example = "10")
        long count) {
}

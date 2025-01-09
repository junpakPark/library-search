package com.library.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "검색 요청을 위한 모델")
public record SearchCondition(
        @NotBlank(message = "입력은 비어있을 수 없습니다.")
        @Size(max = 50, message = "입력은 최대 50자를 초과할 수 없습니다.")
        @Schema(description = "검색쿼리", example = "HTTP", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
        String query,
        @NotNull(message = "페이지 번호는 필수입니다.")
        @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.")
        @Max(value = Integer.MAX_VALUE, message = "페이지 번호는 정수 범위 이내여야 합니다.")
        @Schema(description = "페이지 번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 10000)
        Integer page,
        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        @Max(value = 50, message = "페이지 크기는 50 이하여야 합니다.")
        @Schema(description = "페이지 사이즈", example = "10", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 50)
        Integer size
) {
}

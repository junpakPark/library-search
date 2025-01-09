package com.library.service.dto.response;

import com.library.ErrorType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "에러응답")
public record ErrorResponse(
        @Schema(description = "에러 타입", example = "INVALID_PARAMETER")
        ErrorType errorType,
        @Schema(description = "에러 메세지", example = "잘못된 요청입니다")
        String errorMessage
) {
}

package com.library.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.ApiException;
import com.library.ErrorType;
import com.library.KakaoErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class KakaoErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public KakaoErrorDecoder(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(final String methodKey, final Response response) {

        try {
            final byte[] bytes = response.body()
                    .asInputStream()
                    .readAllBytes();
            final String body = new String(bytes, StandardCharsets.UTF_8);
            final KakaoErrorResponse errorResponse = objectMapper.readValue(body, KakaoErrorResponse.class);

            throw new ApiException(
                    errorResponse.message(),
                    ErrorType.EXTERNAL_API,
                    HttpStatus.valueOf(response.status())
            );
        } catch (IOException e) {
            log.error("[Kakao] 에러 메시지 파싱 에러 code={}, request={}, methodKey={} errorMessage={}",
                    response.status(),
                    response.request(),
                    methodKey,
                    e.getMessage()
            );
            throw new ApiException(
                    "카카오 메시지 파싱 에러",
                    ErrorType.EXTERNAL_API,
                    HttpStatus.valueOf(response.status())
            );
        }

    }
}

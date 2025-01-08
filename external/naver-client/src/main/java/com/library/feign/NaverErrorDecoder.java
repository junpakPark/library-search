package com.library.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.NaverErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class NaverErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public NaverErrorDecoder(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(final String methodKey, final Response response) {

        try {
            final byte[] bytes = response.body()
                    .asInputStream()
                    .readAllBytes();
            final String body = new String(bytes, StandardCharsets.UTF_8);
            final NaverErrorResponse errorResponse = objectMapper.readValue(body, NaverErrorResponse.class);

            throw new RuntimeException(errorResponse.errorMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

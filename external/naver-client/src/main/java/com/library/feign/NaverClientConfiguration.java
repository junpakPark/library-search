package com.library.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverClientConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(
            @Value("${external.naver.headers.client-id}") String clientId,
            @Value("${external.naver.headers.client-secret}") String clientSecret
    ) {
        return template -> template.header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret);
    }

}

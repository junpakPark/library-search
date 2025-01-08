package com.library.feign

import feign.RequestInterceptor
import feign.RequestTemplate
import spock.lang.Specification

class NaverClientConfigurationTest extends Specification {

    NaverClientConfiguration configuration

    void setup() {
        configuration = new NaverClientConfiguration()

    }

    def "requestInterceptor의 headers에 key 값들이 적용된다."() {
        given:
        def clientId = "id"
        def clientSecret = "secret"
        def template = new RequestTemplate()

        and:"interceptor를 거치기 전에는 header가 존재하지 않는다."
        template.headers()["X-Naver-Client-Id"] == null
        template.headers()["X-Naver-Client-Secret"] == null

        when: "interceptor를 거친다"
        def interceptor = configuration.requestInterceptor(clientId, clientSecret)
        interceptor.apply(template)

        then:"interceptor를 거친 이후에는 header가 추가된다."
        template.headers()["X-Naver-Client-Id"].contains(clientId)
        template.headers()["X-Naver-Client-Secret"].contains(clientSecret)
    }
}

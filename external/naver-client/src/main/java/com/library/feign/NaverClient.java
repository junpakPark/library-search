package com.library.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverClient", url = "${external.naver.url}", configuration = NaverClientConfiguration.class)
public interface NaverClient {

    @GetMapping("/v1/search/book.json")
    String search(
            @RequestParam("query") String query,
            @RequestParam("display") int display,
            @RequestParam("start") int start
    );

}

package com.library.controller;

import com.library.service.BookApplicationService;
import com.library.service.dto.PageResult;
import com.library.service.dto.request.SearchCondition;
import com.library.service.dto.response.SearchResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookApplicationService bookApplicationService;

    @GetMapping
    public PageResult<SearchResponse> search(@Valid SearchCondition condition) {
        return bookApplicationService.search(condition.query(), condition.page(), condition.size());
    }

}

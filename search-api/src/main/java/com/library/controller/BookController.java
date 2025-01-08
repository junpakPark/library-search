package com.library.controller;

import com.library.service.BookQueryService;
import com.library.service.dto.PageResult;
import com.library.service.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookQueryService bookQueryService;

    @GetMapping
    public PageResult<SearchResponse> search(
            @RequestParam("query") final String query,
            @RequestParam("page") final int page,
            @RequestParam("size") final int size
    ) {
        return bookQueryService.search(query, page, size);
    }

}

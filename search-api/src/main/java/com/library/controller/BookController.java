package com.library.controller;

import com.library.service.BookApplicationService;
import com.library.service.dto.PageResult;
import com.library.service.dto.request.SearchCondition;
import com.library.service.dto.response.SearchResponse;
import com.library.service.dto.response.StatResponse;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookApplicationService bookApplicationService;

    @GetMapping
    public PageResult<SearchResponse> search(@Valid SearchCondition condition) {
        log.info("[BookController] search condition ={}", condition);
        return bookApplicationService.search(condition.query(), condition.page(), condition.size());
    }

    @GetMapping("/stats")
    public StatResponse findQueryStats(
            @RequestParam String query,
            @RequestParam LocalDate date
    ) {
        log.info("[BookController] find stats query ={}, date={}", query, date);
        return bookApplicationService.findQueryCount(query, date);
    }

    @GetMapping("/stats/ranking")
    public List<StatResponse> findTop5Stats() {
        log.info("[BookController] find top 5 stats");
        return bookApplicationService.findTop5Query();
    }

}

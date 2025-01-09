package com.library.service;

import com.library.entity.DailyStat;
import com.library.service.dto.PageResult;
import com.library.service.dto.response.SearchResponse;
import com.library.service.dto.response.StatResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookApplicationService {

    private final BookQueryService bookQueryService;
    private final DailyStatCommandService dailyStatCommandService;
    private final DailyStatQueryService dailyStatQueryService;

    public PageResult<SearchResponse> search(final String query, final int page, final int size) {
        final PageResult<SearchResponse> result = bookQueryService.search(query, page, size);
        dailyStatCommandService.save(new DailyStat(query, LocalDateTime.now()));

        return result;
    }

    public StatResponse findQueryCount(final String query, final LocalDate date) {
        return dailyStatQueryService.findQueryCount(query, date);
    }

    public List<StatResponse> findTop5Query() {
        return dailyStatQueryService.findTop5Query();
    }

}

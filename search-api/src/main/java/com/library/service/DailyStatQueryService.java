package com.library.service;

import com.library.repository.DailyStatRepository;
import com.library.service.dto.response.StatResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DailyStatQueryService {

    private static final int PAGE = 0;
    private static final int SIZE = 5;

    private final DailyStatRepository dailyStatRepository;

    public StatResponse findQueryCount(final String query, final LocalDate date) {
        long count = dailyStatRepository.countByQueryAndEventDateTimeBetween(
                query,
                date.atStartOfDay(),
                date.atTime(LocalTime.MAX)
        );

        return new StatResponse(query, count);
    }

    public List<StatResponse> findTop5Query() {
        return dailyStatRepository.findTopQuery(PageRequest.of(PAGE, SIZE));
    }

}

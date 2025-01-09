package com.library.service;

import com.library.repository.DailyStatRepository;
import com.library.service.dto.response.StatResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DailyStatQueryService {

    private final DailyStatRepository dailyStatRepository;

    public StatResponse findQueryCount(final String query, final LocalDate date) {
        long count = dailyStatRepository.countByQueryAndEventDateTimeBetween(
                query,
                date.atStartOfDay(),
                date.atTime(LocalTime.MAX)
        );

        return new StatResponse(query, count);
    }

}

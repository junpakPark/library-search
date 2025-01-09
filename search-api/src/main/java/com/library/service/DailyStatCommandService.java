package com.library.service;

import com.library.entity.DailyStat;
import com.library.repository.DailyStatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DailyStatCommandService {

    private final DailyStatRepository dailyStatRepository;

    public void save(final DailyStat dailyStat) {
        log.info("save daily stats: {}", dailyStat);
        dailyStatRepository.save(dailyStat);
    }
}

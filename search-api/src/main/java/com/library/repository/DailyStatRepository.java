package com.library.repository;

import com.library.entity.DailyStat;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyStatRepository extends JpaRepository<DailyStat, Long> {

    long countByQueryAndEventDateTimeBetween(final String query, final LocalDateTime start, final LocalDateTime end);

}

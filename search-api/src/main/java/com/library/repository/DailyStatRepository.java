package com.library.repository;

import com.library.entity.DailyStat;
import com.library.service.dto.response.StatResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DailyStatRepository extends JpaRepository<DailyStat, Long> {

    long countByQueryAndEventDateTimeBetween(final String query, final LocalDateTime start, final LocalDateTime end);

    @Query("SELECT new com.library.service.dto.response.StatResponse(ds.query, count(ds.query))" +
            "FROM DailyStat ds " +
            "GROUP BY ds.query ORDER BY count(ds.query) DESC")
    List<StatResponse> findTopQuery(Pageable pageable);

}

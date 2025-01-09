package com.library;

import com.library.entity.DailyStat;
import com.library.repository.DailyStatRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final DailyStatRepository dailyStatRepository;


    @Override
    public void run(final String... args) {
        final List<DailyStat> stats = List.of(
                new DailyStat("HTTP", LocalDateTime.now()),
                new DailyStat("HTTP", LocalDateTime.now()),
                new DailyStat("HTTP", LocalDateTime.now()),
                new DailyStat("HTTP", LocalDateTime.now()),
                new DailyStat("HTTP", LocalDateTime.now()),
                new DailyStat("HTTP", LocalDateTime.now()),
                new DailyStat("HTTP", LocalDateTime.now()),
                new DailyStat("JAVA", LocalDateTime.now()),
                new DailyStat("JAVA", LocalDateTime.now()),
                new DailyStat("Kotlin", LocalDateTime.now()),
                new DailyStat("Database", LocalDateTime.now()),
                new DailyStat("OS", LocalDateTime.now())
        );
        dailyStatRepository.saveAll(stats);
    }
}

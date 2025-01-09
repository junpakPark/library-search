package com.library.service

import com.library.repository.DailyStatRepository
import org.springframework.data.domain.Pageable
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime

class DailyStatQueryServiceTest extends Specification {
    DailyStatQueryService dailyStatQueryService

    DailyStatRepository dailyStatRepository = Mock(DailyStatRepository)

    void setup() {
        dailyStatQueryService = new DailyStatQueryService(dailyStatRepository)
    }

    def "findQueryCount 조회시 하루치를 조회하면서 쿼리개수가 반환된다."() {
        given:
        def givenQuery = 'HTTP'
        def givenDate = LocalDate.of(2024, 5, 1)
        def expectedCount = 10

        when:
        def response = dailyStatQueryService.findQueryCount(givenQuery, givenDate)

        then:
        1 * dailyStatRepository.countByQueryAndEventDateTimeBetween(
                givenQuery,
                LocalDateTime.of(2024, 5, 1, 0, 0, 0),
                LocalDateTime.of(2024, 5, 1, 23, 59, 59, 999999999),
        ) >> expectedCount

        and:
        response.count() == expectedCount
    }

    def "findTop5Query 조회시 상위 5개 반환 요청이 들어간다."() {
        when:
        dailyStatQueryService.findTop5Query()

        then:
        1 * dailyStatRepository.findTopQuery(*_) >> { Pageable pageable ->
            assert pageable.getPageNumber() == 0
            assert pageable.getPageSize() == 5
        }
    }

}

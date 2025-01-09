package com.library.service

import com.library.entity.DailyStat
import spock.lang.Specification

import java.time.LocalDate

class BookApplicationServiceTest extends Specification {
    BookApplicationService bookApplicationService

    BookQueryService bookQueryService = Mock(BookQueryService)
    DailyStatCommandService dailyStatCommandService = Mock(DailyStatCommandService)
    DailyStatQueryService dailyStatQueryService = Mock(DailyStatQueryService)

    void setup() {
        bookApplicationService = new BookApplicationService(bookQueryService, dailyStatCommandService, dailyStatQueryService)
    }

    def "search메서드 호출시 검색결과를 반환하면서 통계데이터를 저장한다."() {
        given:
        def givenQuery = "HTTP"
        def givenPage = 1
        def givenSize = 10

        when:
        bookApplicationService.search(givenQuery, givenPage, givenSize)

        then:
        1 * bookQueryService.search(*_) >> {
            String query, int page, int size ->
                assert query == givenQuery
                assert page == givenPage
                assert size == givenSize
        }

        and:
        1 * dailyStatCommandService.save(*_) >> {
            DailyStat dailyStat ->
                assert dailyStat.query == givenQuery
        }
    }

    def "findQueryCount메서드 호출시 인자를 그대로 넘긴다"() {
        given:
        def givenQuery = 'HTTP'
        def givenDate = LocalDate.of(2024, 5, 1)

        when:
        bookApplicationService.findQueryCount(givenQuery, givenDate)

        then:
        1 * dailyStatQueryService.findQueryCount(*_) >> {
            String query, LocalDate date ->
                assert query == givenQuery
                assert date == givenDate
        }
    }

    def "findTop5Query메서드 호출시 dailyStatQueryService의 findTop5Query가 호출된다."() {
        when:
        bookApplicationService.findTop5Query()

        then:
        1 * dailyStatQueryService.findTop5Query()
    }

}

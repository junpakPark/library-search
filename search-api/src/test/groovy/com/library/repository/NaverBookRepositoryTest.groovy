package com.library.repository

import com.library.Item
import com.library.NaverBookResponse
import com.library.feign.NaverClient
import spock.lang.Specification

import java.time.LocalDate

class NaverBookRepositoryTest extends Specification {
    BookRepository bookRepository

    NaverClient naverClient = Mock()

    void setup() {
        bookRepository = new NaverBookRepository(naverClient)
    }

    def "search호출시 적절한 데이터형식으로 변환한다."() {
        given:
        def response = new NaverBookResponse(
                "Wed, 29 May 2024 21:12:29 +0900",
                2,
                1,
                2,
                List.of(
                        new Item("title1", "link1", "image1", "author1", "discount1", "publisher1", "20250108", "isbn1", "description1"),
                        new Item("title2", "link2", "image2", "author2", "discount2", "publisher2", "20250108", "isbn2", "description2"),
                )
        )

        and:
        1 * naverClient.search("HTTP", 2, 1) >> response

        when:
        def result = bookRepository.search("HTTP", 1, 2)

        then:
        verifyAll {
            result.size() == 2
            result.page() == 1
            result.totalElements() == 2
            result.contents().size() == 2
            result.contents().get(0).pubDate() == LocalDate.of(2025, 1, 8)
        }
    }
}

package com.library.controller

import com.library.service.BookQueryService
import com.library.service.dto.PageResult
import com.library.service.dto.request.SearchCondition
import com.library.service.dto.response.SearchResponse
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerItTest extends Specification {
    @Autowired
    MockMvc mockMvc

    @SpringBean
    BookQueryService bookQueryService = Mock()

    def "정상인자로 요청시 성공한다."() {
        given:
        def condition = new SearchCondition("HTTP", 1, 10)

        and:
        bookQueryService.search(*_) >> new PageResult<>(10, 1, 10, [Mock(SearchResponse)])

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/books")
                .param("query", condition.query())
                .param("page", condition.page().toString())
                .param("size", condition.size().toString()))

        then:
        result.andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.totalElements').value(10))
                .andExpect(jsonPath('$.page').value(1))
                .andExpect(jsonPath('$.size').value(10))
                .andExpect(jsonPath('$.contents').isArray())
    }

    def "query가 비어있을때 BadRequest 응답반환된다."() {
        given:
        def condition = new SearchCondition("", 1, 10)

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/books")
                .param("query", condition.query())
                .param("page", condition.page().toString())
                .param("size", condition.size().toString()))

        then:
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath('$.errorMessage').value("입력은 비어있을 수 없습니다."))
    }

    def "page가 음수일경우에 BadRequest 응답반환된다."() {
        given:
        def condition = new SearchCondition("HTTP", -10, 10)

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/books")
                .param("query", condition.query())
                .param("page", condition.page().toString())
                .param("size", condition.size().toString()))

        then:
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath('$.errorMessage').value("페이지 번호는 1 이상이어야 합니다."))
    }

    def "size가 50을 초과하면 BadRequest 응답반환된다."() {
        given:
        def request = new SearchCondition("HTTP", 1, 51)

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/books")
                .param("query", request.query())
                .param("page", request.page().toString())
                .param("size", request.size().toString()))

        then:
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath('$.errorMessage').value("페이지 크기는 50 이하여야 합니다."))
    }
}

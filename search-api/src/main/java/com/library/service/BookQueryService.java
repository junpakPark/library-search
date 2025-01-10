package com.library.service;

import com.library.repository.BookRepository;
import com.library.service.dto.PageResult;
import com.library.service.dto.response.SearchResponse;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookQueryService {

    @Qualifier("naverBookRepository")
    private final BookRepository naverBookRepository;
    @Qualifier("kakaoBookRepository")
    private final BookRepository kakaoBookRepository;

    @CircuitBreaker(name = "naverSearch", fallbackMethod = "searchFallBack")
    public PageResult<SearchResponse> search(final String query, final int page, final int size) {
        log.info("[BookQueryService] [search] query = {}, page = {}, size = {}", query, page, size);
        return naverBookRepository.search(query, page, size);
    }

    public PageResult<SearchResponse> searchFallBack(String query, int page, int size, Throwable throwable) {
        if (throwable instanceof CallNotPermittedException) {
            return handleOpenCircuit(query, page, size);
        }
        return handleException(query, page, size, throwable);
    }

    private PageResult<SearchResponse> handleOpenCircuit(String query, int page, int size) {
        log.warn("[BookQueryService] Circuit Breaker is open! Fallback to kakao search.");
        return kakaoBookRepository.search(query, page, size);
    }

    private PageResult<SearchResponse> handleException(String query, int page, int size, Throwable throwable) {
        log.error(
                "[BookQueryService] An error occurred! Fallback to kakao search. errorMessage={}",
                throwable.getMessage()
        );
        return kakaoBookRepository.search(query, page, size);
    }

}

package com.library.repository;

import com.library.NaverBookResponse;
import com.library.feign.NaverClient;
import com.library.service.dto.PageResult;
import com.library.service.dto.response.SearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NaverBookRepository implements BookRepository {

    private final NaverClient naverClient;

    @Override
    public PageResult<SearchResponse> search(final String query, final int page, final int size) {

        final NaverBookResponse response = naverClient.search(query, size, page);
        final List<SearchResponse> results = response.items().stream()
                .map(SearchResponse::from)
                .toList();

        return new PageResult<>(size, page, response.total(), results);
    }
}

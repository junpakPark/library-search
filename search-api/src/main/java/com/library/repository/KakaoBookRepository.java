package com.library.repository;

import com.library.KakaoBookResponse;
import com.library.feign.KakaoClient;
import com.library.service.dto.PageResult;
import com.library.service.dto.response.SearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KakaoBookRepository implements BookRepository {

    private final KakaoClient kakaoClient;

    @Override
    public PageResult<SearchResponse> search(final String query, final int page, final int size) {
        final KakaoBookResponse response = kakaoClient.search(query, page, size);
        final List<SearchResponse> results = response.documents().stream()
                .map(SearchResponse::from)
                .toList();

        return new PageResult<>(size, page, response.meta().totalCount(), results);
    }

}

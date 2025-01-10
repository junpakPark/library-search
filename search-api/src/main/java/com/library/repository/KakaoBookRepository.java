package com.library.repository;

import com.library.service.dto.PageResult;
import com.library.service.dto.response.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KakaoBookRepository implements BookRepository {

    @Override
    public PageResult<SearchResponse> search(final String query, final int page, final int size) {
        return null;
    }

}

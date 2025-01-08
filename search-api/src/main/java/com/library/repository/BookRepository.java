package com.library.repository;

import com.library.service.dto.PageResult;
import com.library.service.dto.SearchResponse;

public interface BookRepository {

    PageResult<SearchResponse> search(final String query, final int page, final int size);

}

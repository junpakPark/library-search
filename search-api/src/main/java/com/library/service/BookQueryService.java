package com.library.service;

import com.library.repository.BookRepository;
import com.library.service.dto.PageResult;
import com.library.service.dto.response.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookQueryService {

    private final BookRepository bookRepository;

    public PageResult<SearchResponse> search(final String query, final int page, final int size) {
        return bookRepository.search(query, page, size);
    }

}

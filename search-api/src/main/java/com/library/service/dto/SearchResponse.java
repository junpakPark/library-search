package com.library.service.dto;

import com.library.Item;
import com.library.util.DateUtils;
import java.time.LocalDate;

public record SearchResponse(
        String title,
        String author,
        String publisher,
        LocalDate pubDate,
        String isbn
) {

    public static SearchResponse from(final Item item) {
        return new SearchResponse(
                item.title(),
                item.author(),
                item.publisher(),
                DateUtils.parseYYYYMMDD(item.pubDate()),
                item.isbn()
        );
    }

}

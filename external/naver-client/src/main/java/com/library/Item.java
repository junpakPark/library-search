package com.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Item(
        String title,
        String link,
        String image,
        String author,
        String discount,
        String publisher,
        @JsonProperty("pubdate")
        String pubDate,
        String isbn,
        String description
) {
}

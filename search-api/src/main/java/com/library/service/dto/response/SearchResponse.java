package com.library.service.dto.response;

import com.library.Document;
import com.library.Item;
import com.library.util.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "검색결과")
public record SearchResponse(
        @Schema(description = "제목", example = "HTTP완벽가이드")
        String title,
        @Schema(description = "저자", example = "데이빗고울리")
        String author,
        @Schema(description = "출판사", example = "인사이트")
        String publisher,
        @Schema(description = "출판일", example = "2015-01-01")
        LocalDate pubDate,
        @Schema(description = "isbn", example = "9788966261208")
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

    public static SearchResponse from(final Document document) {
        return new SearchResponse(
                document.title(),
                getAuthor(document.authors()),
                document.publisher(),
                DateUtils.parseOffsetDateTime(document.datetime()).toLocalDate(),
                document.isbn()
        );
    }

    private static String getAuthor(final List<String> authors) {
        if (authors.isEmpty()) {
            return "";
        }
        return authors.get(0);
    }

}

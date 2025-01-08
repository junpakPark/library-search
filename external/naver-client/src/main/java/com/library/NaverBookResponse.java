package com.library;

import java.util.List;

public record NaverBookResponse(
        String lastBuildDate,
        int total,
        int start,
        int display,
        List<Item> items
) {
}

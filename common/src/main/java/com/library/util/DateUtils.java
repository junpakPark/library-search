package com.library.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils() {
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static LocalDate parseYYYYMMDD(final String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public static LocalDateTime parseOffsetDateTime(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

}

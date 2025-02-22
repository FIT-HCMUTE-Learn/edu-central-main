package com.landingis.api.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class FormatUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    public String formatDate(LocalDate date) {
        return (date != null) ? date.format(FORMATTER) : null;
    }

    public LocalDate parseDate(String dateStr) {
        return (dateStr != null && !dateStr.isEmpty()) ? LocalDate.parse(dateStr, FORMATTER) : null;
    }
}

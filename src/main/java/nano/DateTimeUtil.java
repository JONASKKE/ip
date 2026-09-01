package nano;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides utilities for parsing and formatting dates and times.
 */
public class DateTimeUtil {
    public static final DateTimeFormatter STORAGE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Formats the specified date and time.
     *
     * @param dateTime date and time to format.
     * @return formatted date and time.
     */
    public static String formatForStorage(LocalDateTime dateTime) {
        return dateTime.format(STORAGE_FORMATTER);
    }

    /**
     * Formats the specified date and time.
     *
     * @param dateTime date and time to format.
     * @return formatted date and time.
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMATTER);
    }
}

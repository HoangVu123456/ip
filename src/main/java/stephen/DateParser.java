package stephen;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Parses date strings in various formats (Mon, Monday, next Friday, d/M/yyyy HHmm).
 */
public class DateParser {
    private static final DateTimeFormatter STANDARD_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Parses a date string that can be in natural or standard format.
     * Natural formats include day names (Monday), abbreviations (Mon), relative terms.
     * Standard format is "d/M/yyyy HHmm".
     */
    public static LocalDateTime parseDate(String dateString, String timeString) {
        assert dateString != null : "Date string should not be null";
        LocalTime time = LocalTime.MIDNIGHT;
        String datePartOnly = dateString;
        String[] parts = dateString.trim().split("\\s+");

        if (parts.length >= 2) {
            String lastPart = parts[parts.length - 1];
            LocalTime parsedTime = tryParseTime(lastPart);
            if (parsedTime != null) {
                time = parsedTime;
                datePartOnly = dateString.substring(0, dateString.lastIndexOf(lastPart)).trim();
            }
        }

        if (timeString != null && !timeString.isEmpty()) {
            LocalTime explicitTime = tryParseTime(timeString);
            if (explicitTime != null) {
                time = explicitTime;
            }
        }

        LocalDate date = parseNaturalDate(datePartOnly);

        if (date != null) {
            return LocalDateTime.of(date, time);
        }

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateString, STANDARD_FORMAT);
            return dateTime;
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not parse date: " + dateString);
        }
    }

    /**
     * Attempts to parse a time string in HH:mm or HHmm format.
     * Returns null if parsing fails.
     */
    private static LocalTime tryParseTime(String timeString) {
        try {
            return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e1) {
            try {
                return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
            } catch (Exception e2) {
                return null;
            }
        }
    }

    /**
     * Parses natural date formats.
     * Supported: day names (Monday), abbrevations (Mon), relative terms.
     */
    private static LocalDate parseNaturalDate(String dateString) {
        try {
            String trimmed = dateString.trim().toLowerCase();
            LocalDate today = LocalDate.now();

            if (trimmed.equals("today")) {
                return today;
            }

            if (trimmed.equals("tomorrow")) {
                return today.plusDays(1);
            }

            if (trimmed.startsWith("next ")) {
                String dayName = trimmed.substring(5).trim();
                return getNextDayOfWeek(dayName, today);
            }
            return getNextDayOfWeek(trimmed, today);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Gets the next occurrence of a given day of week.
     * If today is that day, returns today.
     */
    private static LocalDate getNextDayOfWeek(String dayName, LocalDate referenceDate) {
        DayOfWeek targetDay = parseDayOfWeek(dayName);
        DayOfWeek currentDay = referenceDate.getDayOfWeek();
        int daysUntil = targetDay.getValue() - currentDay.getValue();

        if (daysUntil < 0) {
            daysUntil += 7;
        }
        return referenceDate.plusDays(daysUntil);
    }

    /**
     * Parses day of week from various formats (Mon, Monday, mon, MONDAY, etc.)
     * Returns null if the day name is not recognized.
     */
    private static DayOfWeek parseDayOfWeek(String dayName) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase(dayName)) {
                return day;
            }
        }

        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).equalsIgnoreCase(dayName)) {
                return day;
            }
        }

        try {
            return DayOfWeek.valueOf(dayName.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

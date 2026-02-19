package stephen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for DateParser.
 */
public class DateParserTest {
    /**
     * Test for parsing standard date-time format.
     */
    @Test
    public void testStandardDateTimeParsing() {
        LocalDateTime dt = DateParser.parseDate("1/2/2026 2359", null);
        assertEquals(LocalDateTime.of(2026, 2, 1, 23, 59), dt);
    }

    /**
     * Test for parsing date with normal language.
     */
    @Test
    public void testNaturalDateParsing() {
        LocalDateTime dt = DateParser.parseDate("tomorrow", null);
        assertEquals(LocalDate.now().plusDays(1).atTime(LocalTime.MIDNIGHT), dt);
    }

    /**
     * Test for parsing date with normal language and time suffix.
     */
    @Test
    public void testNaturalDateWithTimeSuffix() {
        LocalDateTime dt = DateParser.parseDate("next monday 1800", null);
        assertNotNull(dt);
        assertEquals(LocalTime.of(18, 0), dt.toLocalTime());
    }

    /**
     * Test for parsing invalid date formats.
     */
    @Test
    public void testInvalidDateTimeThrows() {
        assertThrows(IllegalArgumentException.class, () -> DateParser.parseDate("32/13/2026 2500", null));
    }
}

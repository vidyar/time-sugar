package org.watermint.timesugar;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Parser.
 */
public class TimeSugar {
    private List<DateTimeFormatter> formatters = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param patterns acceptable date/time format patterns.
     */
    public TimeSugar(String... patterns) {
        this(Arrays.asList(patterns));
    }

    /**
     * Constructor.
     *
     * @param patterns acceptable date/time format patterns.
     */
    public TimeSugar(Iterable<String> patterns) {
        for (String pattern : patterns) {
            formatters.add(DateTimeFormatter.ofPattern(pattern));
        }
    }

    /**
     * @param instant instant.
     * @param pattern format string.
     * @param zoneId  time zone.
     * @return formatted string
     */
    public static String format(Instant instant, String pattern, ZoneId zoneId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zoneId);
        return dateTime.format(formatter);
    }

    /**
     * parse with patterns with default time zone.
     *
     * @param text     text.
     * @param patterns date/time pattern.
     * @return instant
     */
    public static Optional<Instant> parseWithPatterns(String text, String... patterns) {
        return new TimeSugar(patterns).parse(text);
    }

    /**
     * parse with patterns with default time zone.
     *
     * @param text     text.
     * @param patterns date/time pattern.
     * @return instant
     */
    public static Optional<Instant> parseWithPatterns(String text, Iterable<String> patterns) {
        return new TimeSugar(patterns).parse(text);
    }

    /**
     * parse with patterns with default time zone.
     *
     * @param text     text.
     * @param patterns date/time pattern.
     * @return instant
     */
    public static Optional<Instant> parseWithPatterns(String text, ZoneId zoneId, String... patterns) {
        return new TimeSugar(patterns).parse(text, zoneId);
    }

    /**
     * parse with patterns with default time zone.
     *
     * @param text     text.
     * @param patterns date/time pattern.
     * @return instant
     */
    public static Optional<Instant> parseWithPatterns(String text, ZoneId zoneId, Iterable<String> patterns) {
        return new TimeSugar(patterns).parse(text, zoneId);
    }

    /**
     * parse with patterns with default time zone.
     *
     * @param text     text.
     * @param patterns date/time pattern.
     * @return instant
     */
    public static Optional<Instant> parseWithPatterns(String text, ZoneOffset zoneOffset, String... patterns) {
        return new TimeSugar(patterns).parse(text, zoneOffset);
    }

    /**
     * parse with patterns with default time zone.
     *
     * @param text     text.
     * @param patterns date/time pattern.
     * @return instant
     */
    public static Optional<Instant> parseWithPatterns(String text, ZoneOffset zoneOffset, Iterable<String> patterns) {
        return new TimeSugar(patterns).parse(text, zoneOffset);
    }

    /**
     * parse as local date/time with default time zone.
     *
     * @param text text.
     * @return instant as optional.
     */
    public Optional<Instant> parse(String text) {
        return parse(text, ZoneId.systemDefault());
    }

    /**
     * parse as local date/time.
     *
     * @param text   text.
     * @param zoneId time zone id.
     * @return instant as optional.
     */
    public Optional<Instant> parse(String text, ZoneId zoneId) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDateTime ldt = LocalDateTime.parse(text, formatter);
                return Optional.of(ldt.toInstant(zoneId.getRules().getOffset(ldt)));
            } catch (DateTimeParseException e) {
                // ignore
            }
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate ld = LocalDate.parse(text, formatter);
                LocalDateTime ldt = ld.atTime(0, 0);
                return Optional.of(ldt.toInstant(zoneId.getRules().getOffset(ldt)));
            } catch (DateTimeParseException e) {
                // ignore
            }
        }

        return Optional.empty();
    }

    /**
     * parse as local date/time.
     *
     * @param text       text.
     * @param zoneOffset time zone offset.
     * @return instant as optional.
     */
    public Optional<Instant> parse(String text, ZoneOffset zoneOffset) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDateTime ldt = LocalDateTime.parse(text, formatter);
                return Optional.of(ldt.toInstant(zoneOffset));
            } catch (DateTimeParseException e) {
                // ignore
            }
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate ld = LocalDate.parse(text, formatter);
                LocalDateTime ldt = ld.atTime(0, 0);
                return Optional.of(ldt.toInstant(zoneOffset));
            } catch (DateTimeParseException e) {
                // ignore
            }
        }

        return Optional.empty();
    }
}

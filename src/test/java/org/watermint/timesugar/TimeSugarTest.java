package org.watermint.timesugar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TimeSugarTest {
    @Test
    public void format() {
        assertEquals("2013-10-13",
                TimeSugar.format(
                        TimeSugar.parseWithPatterns("2013-10-13", "yyyy-MM-dd").get(),
                        "yyyy-MM-dd",
                        ZoneId.of("Asia/Tokyo")));

        assertEquals("2013-10-13 15:48:37",
                TimeSugar.format(
                        TimeSugar.parseWithPatterns("2013-10-13 15:48:37", "yyyy-MM-dd HH:mm:ss").get(),
                        "yyyy-MM-dd HH:mm:ss",
                        ZoneId.of("Asia/Tokyo")));
    }

    @Test
    public void datePattern() {
        TimeSugar ts = new TimeSugar("yyyy/MM/dd");
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 0, 0);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));

        assertTrue(ts.parse("2013/10/13").isPresent());
        assertEquals(oct13, ts.parse("2013/10/13").get());

        assertTrue(ts.parse("2013/10/13", ZoneId.systemDefault()).isPresent());
        assertTrue(ts.parse("2013/10/13", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(ts.parse("2013/10/13", ZoneOffset.of("+09:00")).isPresent());

        assertFalse(ts.parse("2013-10-13").isPresent());
    }

    @Test
    public void datePatterns() {
        TimeSugar ts = new TimeSugar("yyyy/MM/dd", "yyyy-MM-dd");
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 0, 0);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));

        assertTrue(ts.parse("2013/10/13").isPresent());
        assertTrue(ts.parse("2013-10-13").isPresent());
        assertEquals(oct13, ts.parse("2013/10/13").get());
        assertEquals(oct13, ts.parse("2013-10-13").get());

        assertTrue(ts.parse("2013/10/13", ZoneId.systemDefault()).isPresent());
        assertTrue(ts.parse("2013/10/13", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(ts.parse("2013/10/13", ZoneOffset.of("+09:00")).isPresent());
        assertTrue(ts.parse("2013-10-13", ZoneId.systemDefault()).isPresent());
        assertTrue(ts.parse("2013-10-13", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(ts.parse("2013-10-13", ZoneOffset.of("+09:00")).isPresent());

        assertFalse(ts.parse("2013#10#13").isPresent());
    }

    @Test
    public void dateTimePattern() {
        TimeSugar ts = new TimeSugar("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 17, 19, 23);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));

        assertTrue(ts.parse("2013/10/13 17:19:23").isPresent());
        assertEquals(oct13, ts.parse("2013/10/13 17:19:23").get());

        assertTrue(ts.parse("2013/10/13 17:19:23", ZoneId.systemDefault()).isPresent());
        assertTrue(ts.parse("2013/10/13 17:19:23", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(ts.parse("2013/10/13 17:19:23", ZoneOffset.of("+09:00")).isPresent());

        assertFalse(ts.parse("2013-10-13 17:19:23").isPresent());
    }

    @Test
    public void dateTimePatterns() {
        TimeSugar ts = new TimeSugar("yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss");
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 17, 19, 23);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));

        assertTrue(ts.parse("2013/10/13 17:19:23").isPresent());
        assertTrue(ts.parse("2013-10-13 17:19:23").isPresent());
        assertEquals(oct13, ts.parse("2013/10/13 17:19:23").get());
        assertEquals(oct13, ts.parse("2013-10-13 17:19:23").get());

        assertTrue(ts.parse("2013/10/13 17:19:23", ZoneId.systemDefault()).isPresent());
        assertTrue(ts.parse("2013/10/13 17:19:23", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(ts.parse("2013/10/13 17:19:23", ZoneOffset.of("+09:00")).isPresent());

        assertFalse(ts.parse("2013=10=13 17:19:23").isPresent());
    }

    @Test
    public void directFunctions() {
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 17, 19, 23);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));
        String[] formatArray = {"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss"};
        List<String> formatList = Arrays.asList(formatArray);

        assertTrue(TimeSugar.parseWithPatterns("2013/10/13", formatList).isPresent());
        assertTrue(TimeSugar.parseWithPatterns("2013/10/13", formatArray).isPresent());
        assertEquals(oct13, TimeSugar.parseWithPatterns("2013/10/13 17:19:23", ZoneId.of("Asia/Tokyo"), formatList).get());
        assertEquals(oct13, TimeSugar.parseWithPatterns("2013/10/13 17:19:23", ZoneId.of("Asia/Tokyo"), formatArray).get());
        assertEquals(oct13, TimeSugar.parseWithPatterns("2013/10/13 17:19:23", ZoneOffset.of("+09:00"), formatList).get());
        assertEquals(oct13, TimeSugar.parseWithPatterns("2013/10/13 17:19:23", ZoneOffset.of("+09:00"), formatArray).get());
    }
}

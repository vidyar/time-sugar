package org.watermint.timesugar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class SugarParserTest {
    @Test
    public void datePattern() {
        SugarParser sp = new SugarParser("yyyy/MM/dd");
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 0, 0);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));

        assertTrue(sp.parse("2013/10/13").isPresent());
        assertEquals(oct13, sp.parse("2013/10/13").get());

        assertTrue(sp.parse("2013/10/13", ZoneId.systemDefault()).isPresent());
        assertTrue(sp.parse("2013/10/13", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(sp.parse("2013/10/13", ZoneOffset.of("+09:00")).isPresent());

        assertFalse(sp.parse("2013-10-13").isPresent());
    }

    @Test
    public void datePatterns() {
        SugarParser sp = new SugarParser("yyyy/MM/dd", "yyyy-MM-dd");
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 0, 0);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));

        assertTrue(sp.parse("2013/10/13").isPresent());
        assertTrue(sp.parse("2013-10-13").isPresent());
        assertEquals(oct13, sp.parse("2013/10/13").get());
        assertEquals(oct13, sp.parse("2013-10-13").get());

        assertTrue(sp.parse("2013/10/13", ZoneId.systemDefault()).isPresent());
        assertTrue(sp.parse("2013/10/13", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(sp.parse("2013/10/13", ZoneOffset.of("+09:00")).isPresent());
        assertTrue(sp.parse("2013-10-13", ZoneId.systemDefault()).isPresent());
        assertTrue(sp.parse("2013-10-13", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(sp.parse("2013-10-13", ZoneOffset.of("+09:00")).isPresent());

        assertFalse(sp.parse("2013#10#13").isPresent());
    }

    @Test
    public void dateTimePattern() {
        SugarParser sp = new SugarParser("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 17, 19, 23);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));

        assertTrue(sp.parse("2013/10/13 17:19:23").isPresent());
        assertEquals(oct13, sp.parse("2013/10/13 17:19:23").get());

        assertTrue(sp.parse("2013/10/13 17:19:23", ZoneId.systemDefault()).isPresent());
        assertTrue(sp.parse("2013/10/13 17:19:23", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(sp.parse("2013/10/13 17:19:23", ZoneOffset.of("+09:00")).isPresent());

        assertFalse(sp.parse("2013-10-13 17:19:23").isPresent());
    }

    @Test
    public void dateTimePatterns() {
        SugarParser sp = new SugarParser("yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss");
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 17, 19, 23);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));

        assertTrue(sp.parse("2013/10/13 17:19:23").isPresent());
        assertTrue(sp.parse("2013-10-13 17:19:23").isPresent());
        assertEquals(oct13, sp.parse("2013/10/13 17:19:23").get());
        assertEquals(oct13, sp.parse("2013-10-13 17:19:23").get());

        assertTrue(sp.parse("2013/10/13 17:19:23", ZoneId.systemDefault()).isPresent());
        assertTrue(sp.parse("2013/10/13 17:19:23", ZoneId.of("Asia/Tokyo")).isPresent());
        assertTrue(sp.parse("2013/10/13 17:19:23", ZoneOffset.of("+09:00")).isPresent());

        assertFalse(sp.parse("2013=10=13 17:19:23").isPresent());
    }

    @Test
    public void directFunctions() {
        LocalDateTime localOct13 = LocalDateTime.of(2013, 10, 13, 17, 19, 23);
        Instant oct13 = localOct13.toInstant(ZoneId.systemDefault().getRules().getOffset(localOct13));
        String[] formatArray = {"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss"};
        List<String> formatList = Arrays.asList(formatArray);

        assertTrue(SugarParser.parseWithPatterns("2013/10/13", formatList).isPresent());
        assertTrue(SugarParser.parseWithPatterns("2013/10/13", formatArray).isPresent());
        assertEquals(oct13, SugarParser.parseWithPatterns("2013/10/13 17:19:23", ZoneId.of("Asia/Tokyo"), formatList).get());
        assertEquals(oct13, SugarParser.parseWithPatterns("2013/10/13 17:19:23", ZoneId.of("Asia/Tokyo"), formatArray).get());
        assertEquals(oct13, SugarParser.parseWithPatterns("2013/10/13 17:19:23", ZoneOffset.of("+09:00"), formatList).get());
        assertEquals(oct13, SugarParser.parseWithPatterns("2013/10/13 17:19:23", ZoneOffset.of("+09:00"), formatArray).get());
    }
}

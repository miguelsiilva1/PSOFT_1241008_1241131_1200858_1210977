package com.marslps.AISafeFMS.entities;

import com.marslps.AISafeFMS.model.entities.TimeZone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimeZoneTest {

    @Test
    void testValidTimeZone() {
        TimeZone tz = new TimeZone("GMT", 0, 0);
        assertEquals("GMT", tz.getAbbreviation());
        assertEquals(0, tz.getUtc_offset_minutes());
        assertEquals(0, tz.getGmt_offset_minutes());
    }

    @Test
    void testEquals() {
        TimeZone tz1 = new TimeZone("GMT", 0, 0);
        TimeZone tz2 = new TimeZone("GMT", 60, 60);
        
        assertTrue(tz1.equals(tz2));
        assertEquals(tz1.hashCode(), tz2.hashCode());
    }

    @Test
    void testNotEquals() {
        TimeZone tz1 = new TimeZone("GMT", 0, 0);
        TimeZone tz2 = new TimeZone("CET", 0, 0);
        
        assertFalse(tz1.equals(tz2));
        assertNotEquals(tz1.hashCode(), tz2.hashCode());
    }
}
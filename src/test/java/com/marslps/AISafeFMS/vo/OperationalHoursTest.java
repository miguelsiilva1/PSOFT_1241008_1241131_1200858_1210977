package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.OperationalHours;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationalHoursTest {

    @Test
    void testValidOperationalHours() {
        OperationalHours hours = new OperationalHours(6, 23);
        assertEquals(6, hours.opening_hours());
        assertEquals(23, hours.closing_hours());
    }
}
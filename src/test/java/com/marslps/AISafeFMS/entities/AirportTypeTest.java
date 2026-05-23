package com.marslps.AISafeFMS.entities;

import com.marslps.AISafeFMS.model.entities.AirportType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirportTypeTest {

    @Test
    void testValidAirportType() {
        AirportType type = new AirportType("Civil");
        assertEquals("Civil", type.getType());
    }
}
package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.AirportLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportLocationTest {

    @Test
    void testValidLocation() {
        AirportLocation location = new AirportLocation("Porto", "Portugal", "Europa");
        assertEquals("Porto", location.city());
        assertEquals("Portugal", location.country());
        assertEquals("Europa", location.region());
    }
}
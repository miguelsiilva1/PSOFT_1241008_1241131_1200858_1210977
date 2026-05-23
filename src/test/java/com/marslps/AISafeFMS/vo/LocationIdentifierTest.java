package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocationIdentifierTest {

    @Test
    void testValidIataCode() {
        LocationIdentifier location = new LocationIdentifier("OPO");
        assertEquals("OPO", location.iata());
    }
}
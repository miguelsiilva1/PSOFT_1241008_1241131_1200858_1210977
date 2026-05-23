package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.Coordinates;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void testValidCoordinates() {
        Coordinates coords = new Coordinates(8.9, 'W', 38.7, 'N');
        assertEquals(8.9, coords.longitude());
        assertEquals('W', coords.longitude_direction());
    }
}
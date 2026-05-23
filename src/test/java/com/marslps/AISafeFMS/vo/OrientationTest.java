package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.Orientation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

    @Test
    void testValidOrientation() {
        Orientation orientation = new Orientation("NE");
        assertEquals("NE", orientation.orientation());
    }
}
package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.Orientation;
import com.marslps.AISafeFMS.model.vo.RunwayInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RunwayInfoTest {

    @Test
    void testValidRunwayInfo() {
        Orientation orientation = new Orientation("NE");
        RunwayInfo runway = new RunwayInfo("03/21", 3805.0, orientation);
        
        assertEquals("03/21", runway.name());
        assertEquals(3805.0, runway.length());
    }
}
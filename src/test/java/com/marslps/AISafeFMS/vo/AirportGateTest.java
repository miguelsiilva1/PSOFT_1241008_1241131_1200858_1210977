package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.AirportGate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirportGateTest {

    @Test
    void testValidGate() {
        AirportGate gate = new AirportGate("G42");
        assertEquals("G42", gate.gate());
    }
}
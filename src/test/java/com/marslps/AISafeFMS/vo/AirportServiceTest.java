package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.AirportService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirportServiceTest {

    @Test
    void testValidService() {
        AirportService service = new AirportService("Free Wi-Fi");
        assertEquals("Free Wi-Fi", service.service());
    }
}
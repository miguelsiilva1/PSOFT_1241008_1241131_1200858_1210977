package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AircraftManufacturerTest {
    @Test
    void ensureAircraftManufacturerNameCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftManufacturer(""));
    }
}

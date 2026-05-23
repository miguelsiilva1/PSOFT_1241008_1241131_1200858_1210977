package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AircraftModelTest {
    @Test
    void ensureAircraftModelSeatingCapacityCannotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel("Boeing 737", -100, 100, 100, 100, new AircraftManufacturer("Boeing"), new HashSet<>()));
    }
    @Test
    void ensureAircraftModelDoublesCannotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel("Boeing 737", 100, -100, 100, 100, new AircraftManufacturer("Boeing"), new HashSet<>()));
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel("Boeing 737", 100, 100, -100, 100, new AircraftManufacturer("Boeing"), new HashSet<>()));
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel("Boeing 737", 100, 100, 100, -100, new AircraftManufacturer("Boeing"), new HashSet<>()));
    }
}

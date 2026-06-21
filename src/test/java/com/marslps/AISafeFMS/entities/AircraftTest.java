package com.marslps.AISafeFMS.entities;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.SeatingConfiguration;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AircraftTest {

    @Test
    void ensureAircraftCannotBeCreatedWithNegativeMaintenanceHours() {
        AircraftRegistration registration = new AircraftRegistration("CS-TBA");
        AircraftModel model = new AircraftModel("Boeing 737", 100, 100, 100, 100, new AircraftManufacturer("Boeing"), new HashSet<>());
        SeatingConfiguration seats = new SeatingConfiguration(100, 0, 0, 0);
        Date manufacturing_date = new Date();

        assertThrows(IllegalArgumentException.class, () -> new Aircraft(
                registration,
                model,
                seats,
                manufacturing_date,
                manufacturing_date,
                "",
                1000.0,
                5000.0,
                -50,
                150,
                AircraftStatus.AVAILABLE
        ));
    }

    @Test
    void ensureAircraftCannotBeCreatedWithNegativeMaintenanceDays() {
        AircraftRegistration registration = new AircraftRegistration("CS-TBA");
        AircraftModel model = new AircraftModel("Boeing 737", 100, 100, 100, 100, new AircraftManufacturer("Boeing"), new HashSet<>());
        SeatingConfiguration seats = new SeatingConfiguration(100, 0, 0, 0);
        Date manufacturing_date = new Date();

        assertThrows(IllegalArgumentException.class, () -> new Aircraft(
                registration,
                model,
                seats,
                manufacturing_date,
                manufacturing_date,
                "",
                1000.0,
                5000.0,
                50,
                -150,
                AircraftStatus.AVAILABLE
        ));
    }
}
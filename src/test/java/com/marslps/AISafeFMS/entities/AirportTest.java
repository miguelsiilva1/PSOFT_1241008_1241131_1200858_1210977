package com.marslps.AISafeFMS.entities;

import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    private Airport airport;
    private AircraftModel aircraft_model;

    @BeforeEach
    void setUp() {
        airport = new Airport(
                "Aeroporto de Teste",
                new LocationIdentifier("OPO"),
                null, null, null,
                new HashSet<>(),
                null,
                AirportStatus.OPERATIONAL,
                null, null, null, null, null, null, null
        );
        
        aircraft_model = new AircraftModel();
    }

    @Test
    void testAddAircraftModel() {
        airport.addCertifiedAircraftModel(aircraft_model);

        assertTrue(airport.obtainCertified_models().contains(aircraft_model));
        assertEquals(1, airport.obtainCertified_models().size());
    }

    @Test
    void testAddDuplicateAircraftModel() {
        airport.addCertifiedAircraftModel(aircraft_model);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            airport.addCertifiedAircraftModel(aircraft_model);
        });
        assertEquals("This Aircraft Model is already certified for this Airport.", exception.getMessage());
    }

    @Test
    void testUpdateStatus() {
        airport.updateStatus(AirportStatus.CLOSED);

        assertEquals(AirportStatus.CLOSED, airport.obtainStatus());
    }

    @Test
    void testUpdateSameStatus() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            airport.updateStatus(AirportStatus.OPERATIONAL);
        });
        assertEquals("The airport is already in the requested status.", exception.getMessage());
    }
    
    @Test
    void testEquals() {
        Airport other_airport = new Airport("Outro", new LocationIdentifier("OPO"), null, null, null, null, null, null, null, null, null, null, null, null, null);
        
        assertTrue(airport.equals(other_airport));
    }
    
    @Test
    void testNotEquals() {
        Airport other_airport = new Airport("Outro", new LocationIdentifier("LIS"), null, null, null, null, null, null, null, null, null, null, null, null, null);
        
        assertFalse(airport.equals(other_airport));
    }
}
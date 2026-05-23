package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.SeatingConfiguration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeatingConfigurationTest {

    @Test
    void ensureSeatingConfigurationCanBeCreatedWithValidData() {
        SeatingConfiguration seats = new SeatingConfiguration(100, 20, 10, 4);

        assertNotNull(seats);
        assertEquals(100, seats.economy_seats());
        assertEquals(20, seats.premium_economy_seats());
        assertEquals(10, seats.business_seats());
        assertEquals(4, seats.first_seats());
    }

    @Test
    void ensureSeatingCannotHaveNegativePremiumEconomySeats() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SeatingConfiguration(10, -20, 10, 4);
        });

        assertEquals("The number of seats cannot be negative.", exception.getMessage());
    }
    @Test
    void ensureSeatingCannotHaveNegativeBusinessSeats() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SeatingConfiguration(10, 20, -10, 4);
        });

        assertEquals("The number of seats cannot be negative.", exception.getMessage());
    }
    @Test
    void ensureSeatingCannotHaveNegativeFirstSeats() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SeatingConfiguration(10, 20, 10, -4);
        });

        assertEquals("The number of seats cannot be negative.", exception.getMessage());
    }
}

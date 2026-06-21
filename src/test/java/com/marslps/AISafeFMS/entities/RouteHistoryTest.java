package com.marslps.AISafeFMS.entities;

import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.entities.RouteHistory;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.model.vo.RouteID;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RouteHistoryTest {

    private Airport airport(String iata) {
        return new Airport(
                "Aeroporto " + iata,
                new LocationIdentifier(iata),
                null,
                null,
                null,
                new HashSet<AircraftModel>(),
                null,
                AirportStatus.OPERATIONAL,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private Route route() {
        return new Route(
                new RouteID("TSA-TSB"),
                airport("TSA"),
                airport("TSB"),
                1.0,
                280.0,
                500.0,
                100,
                true
        );
    }

    @Test
    void testValidRouteHistory() {
        Route route = route();

        RouteHistory history = new RouteHistory(route, "Route created.");

        assertEquals(route, history.getRoute());
        assertEquals("Route created.", history.getDescription());
        assertNotNull(history.getRegistered_at());
    }

    @Test
    void ensureRouteHistoryCannotBeCreatedWithoutRoute() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new RouteHistory(null, "Route created.");
        });

        assertEquals("Route is required.", exception.getMessage());
    }

    @Test
    void ensureRouteHistoryCannotBeCreatedWithoutDescription() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new RouteHistory(route(), "");
        });

        assertEquals("History description is required.", exception.getMessage());
    }

    @Test
    void ensureRouteHistoryCannotBeCreatedWithBlankDescription() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new RouteHistory(route(), "   ");
        });

        assertEquals("History description is required.", exception.getMessage());
    }

    @Test
    void ensureRouteHistoryCannotBeCreatedWithNullDescription() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new RouteHistory(route(), null);
        });

        assertEquals("History description is required.", exception.getMessage());
    }
}
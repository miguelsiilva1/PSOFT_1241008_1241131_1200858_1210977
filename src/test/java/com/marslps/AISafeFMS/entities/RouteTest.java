package com.marslps.AISafeFMS.entities;

import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.model.vo.RouteID;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

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

    @Test
    void testValidRoute() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        Route route = new Route(
                new RouteID("TSA-TSB"),
                departure,
                destination,
                1.0,
                280.0,
                500.0,
                100,
                true
        );

        assertEquals(new RouteID("TSA-TSB"), route.obtainRouteID());
        assertEquals(departure, route.obtainDeparture());
        assertEquals(destination, route.obtainDestination());
        assertTrue(route.isActive());
    }

    @Test
    void ensureRouteCannotBeCreatedWithoutRouteID() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(
                    null,
                    departure,
                    destination,
                    1.0,
                    280.0,
                    500.0,
                    100,
                    true
            );
        });

        assertEquals("Route ID is required.", exception.getMessage());
    }

    @Test
    void ensureRouteCannotBeCreatedWithoutDepartureAirport() {
        Airport destination = airport("TSB");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(
                    new RouteID("TSA-TSB"),
                    null,
                    destination,
                    1.0,
                    280.0,
                    500.0,
                    100,
                    true
            );
        });

        assertEquals("Departure airport is required.", exception.getMessage());
    }

    @Test
    void ensureRouteCannotBeCreatedWithoutDestinationAirport() {
        Airport departure = airport("TSA");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(
                    new RouteID("TSA-TSB"),
                    departure,
                    null,
                    1.0,
                    280.0,
                    500.0,
                    100,
                    true
            );
        });

        assertEquals("Destination airport is required.", exception.getMessage());
    }

    @Test
    void ensureRouteCannotHaveSameDepartureAndDestination() {
        Airport airport = airport("TSA");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(
                    new RouteID("TSA-TSA"),
                    airport,
                    airport,
                    1.0,
                    280.0,
                    500.0,
                    100,
                    true
            );
        });

        assertEquals("Departure and destination airports must be different.", exception.getMessage());
    }

    @Test
    void ensureFlightTimeMustBePositive() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(
                    new RouteID("TSA-TSB"),
                    departure,
                    destination,
                    0.0,
                    280.0,
                    500.0,
                    100,
                    true
            );
        });

        assertEquals("Flight time must be positive.", exception.getMessage());
    }

    @Test
    void ensureFlightDistanceMustBePositive() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(
                    new RouteID("TSA-TSB"),
                    departure,
                    destination,
                    1.0,
                    -280.0,
                    500.0,
                    100,
                    true
            );
        });

        assertEquals("Flight distance must be positive.", exception.getMessage());
    }

    @Test
    void ensureMinimumAircraftRangeMustBePositive() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(
                    new RouteID("TSA-TSB"),
                    departure,
                    destination,
                    1.0,
                    280.0,
                    -500.0,
                    100,
                    true
            );
        });

        assertEquals("Minimum aircraft range must be positive.", exception.getMessage());
    }

    @Test
    void ensureMinimumAircraftCapacityMustBePositive() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(
                    new RouteID("TSA-TSB"),
                    departure,
                    destination,
                    1.0,
                    280.0,
                    500.0,
                    0,
                    true
            );
        });

        assertEquals("Minimum aircraft capacity must be positive.", exception.getMessage());
    }

    @Test
    void testUpdateRouteWithValidData() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        Route route = new Route(
                new RouteID("TSA-TSB"),
                departure,
                destination,
                1.0,
                280.0,
                500.0,
                100,
                true
        );

        assertDoesNotThrow(() -> {
            route.updateRoute(
                    1.2,
                    300.0,
                    550.0,
                    120
            );
        });
    }

    @Test
    void ensureUpdateRouteDoesNotAcceptInvalidData() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        Route route = new Route(
                new RouteID("TSA-TSB"),
                departure,
                destination,
                1.0,
                280.0,
                500.0,
                100,
                true
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            route.updateRoute(
                    -1.0,
                    300.0,
                    550.0,
                    120
            );
        });

        assertEquals("Flight time must be positive.", exception.getMessage());
    }

    @Test
    void testDeactivateRoute() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        Route route = new Route(
                new RouteID("TSA-TSB"),
                departure,
                destination,
                1.0,
                280.0,
                500.0,
                100,
                true
        );

        route.deactivate();

        assertFalse(route.isActive());
    }

    @Test
    void ensureInactiveRouteCannotBeDeactivatedAgain() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        Route route = new Route(
                new RouteID("TSA-TSB"),
                departure,
                destination,
                1.0,
                280.0,
                500.0,
                100,
                false
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, route::deactivate);

        assertEquals("Route is already inactive.", exception.getMessage());
    }

    @Test
    void testEqualsWithSameRouteID() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        Route route1 = new Route(
                new RouteID("TSA-TSB"),
                departure,
                destination,
                1.0,
                280.0,
                500.0,
                100,
                true
        );

        Route route2 = new Route(
                new RouteID("TSA-TSB"),
                departure,
                destination,
                2.0,
                300.0,
                600.0,
                150,
                true
        );

        assertEquals(route1, route2);
        assertEquals(route1.hashCode(), route2.hashCode());
    }

    @Test
    void testNotEqualsWithDifferentRouteID() {
        Airport departure = airport("TSA");
        Airport destination = airport("TSB");

        Route route1 = new Route(
                new RouteID("TSA-TSB"),
                departure,
                destination,
                1.0,
                280.0,
                500.0,
                100,
                true
        );

        Route route2 = new Route(
                new RouteID("TSA-LIS"),
                departure,
                destination,
                1.0,
                280.0,
                500.0,
                100,
                true
        );

        assertNotEquals(route1, route2);
    }
}
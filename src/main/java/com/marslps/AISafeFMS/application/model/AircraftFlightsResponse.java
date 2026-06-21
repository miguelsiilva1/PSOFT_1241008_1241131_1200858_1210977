package com.marslps.AISafeFMS.application.model;

import com.marslps.AISafeFMS.model.entities.Flight;

import java.util.Date;

public record AircraftFlightsResponse(
        String flight_id,
        String route_id,
        String aircraft_registration,
        Date scheduled_departure,
        Date scheduled_arrival,
        String status
) {
    public static AircraftFlightsResponse from(Flight flight) {
        return new AircraftFlightsResponse(
                flight.obtainFlightID().id(),
                flight.obtainRoute().obtainRouteID().id(),
                flight.obtainAircraft().obtainRegistrationNumber(),
                flight.obtainScheduledDeparture(),
                flight.obtainScheduledArrival(),
                flight.obtainStatus().toString()
        );
    }
}
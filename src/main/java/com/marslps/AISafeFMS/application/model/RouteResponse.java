package com.marslps.AISafeFMS.application.model;

import com.marslps.AISafeFMS.model.entities.Route;

public record RouteResponse(
        String route_id,
        String departure_iata,
        String destination_iata,
        double estimated_flight_time,
        double flight_distance,
        double min_aircraft_range,
        int min_aircraft_capacity,
        boolean active
) {
    public static RouteResponse from(Route route) {
        return new RouteResponse(
                route.obtainRouteID().id(),
                route.obtainDeparture().obtainIata().iata(),
                route.obtainDestination().obtainIata().iata(),
                route.getFlight_time(),
                route.getFlight_distance(),
                route.getMin_aircraft_range(),
                route.getMin_aircraft_capacity(),
                route.isActive()
        );
    }
}
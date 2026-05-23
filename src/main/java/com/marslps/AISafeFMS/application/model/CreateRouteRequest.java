package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateRouteRequest(
        @NotBlank String departure_iata,
        @NotBlank String destination_iata,
        @Positive double estimated_flight_time,
        @Positive double flight_distance,
        @Positive double min_aircraft_range,
        @Positive int min_aircraft_capacity
) {
}
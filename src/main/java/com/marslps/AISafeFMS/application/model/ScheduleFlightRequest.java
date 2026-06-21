package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ScheduleFlightRequest(
        @NotBlank String route_id,
        @NotBlank String aircraft_registration,
        @NotNull Date scheduled_departure
) {
}
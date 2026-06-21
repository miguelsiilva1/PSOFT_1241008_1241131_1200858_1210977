package com.marslps.AISafeFMS.application.model;

import java.util.Date;

public record ScheduleFlightResponse(
        String flight_id,
        String route_id,
        String aircraft_registration,
        Date scheduled_departure,
        String status
) {
}
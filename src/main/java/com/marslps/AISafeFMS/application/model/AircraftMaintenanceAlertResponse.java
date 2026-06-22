package com.marslps.AISafeFMS.application.model;

public record AircraftMaintenanceAlertResponse(
        String aircraftRegistration,
        String reason,
        double currentHours,
        long daysSinceLastMaintenance
) {}
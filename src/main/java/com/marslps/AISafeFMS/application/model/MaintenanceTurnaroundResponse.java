package com.marslps.AISafeFMS.application.model;

public record MaintenanceTurnaroundResponse(
        String aircraftType,
        double averageTurnaroundHours
) {}
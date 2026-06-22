package com.marslps.AISafeFMS.application.model;

public record MaintenanceCostReportResponse(
        String target,
        double totalCost
) {}
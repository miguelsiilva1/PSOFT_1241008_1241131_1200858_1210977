package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.Date;

public record CreateMaintenanceRecordRequest(
        @NotBlank String aircraft_registration,
        @Positive int template_id,
        @NotBlank String description,
        Date start_date,
        @Positive double expected_duration
) {}
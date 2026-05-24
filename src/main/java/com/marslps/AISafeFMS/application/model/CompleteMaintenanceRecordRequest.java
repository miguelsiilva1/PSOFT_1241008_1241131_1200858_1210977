package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;

public record CompleteMaintenanceRecordRequest(@NotBlank String completion_notes) {}
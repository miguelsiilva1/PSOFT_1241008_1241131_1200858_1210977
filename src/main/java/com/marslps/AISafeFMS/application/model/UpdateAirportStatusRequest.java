package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;

public record UpdateAirportStatusRequest(
        @NotBlank String status) {}
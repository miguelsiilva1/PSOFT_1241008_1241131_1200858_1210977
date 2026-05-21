package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record RegisterAircraftModelRequest(@NotBlank String name,
                                           @Positive int seating_capacity,
                                           @Positive double max_range,
                                           @Positive double fuel_capacity,
                                           @Positive double cruising_speed,
                                           @NotBlank String manufacturer_name) {}

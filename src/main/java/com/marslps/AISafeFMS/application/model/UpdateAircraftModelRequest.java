package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UpdateAircraftModelRequest(@NotBlank String name,
                                         @Positive int seating_capacity,
                                         @Positive double max_range,
                                         @Positive double fuel_capacity,
                                         @Positive double cruising_speed,
                                         @NotBlank String manufacturer_name,
                                         @Positive int new_seating_capacity,
                                         @Positive double new_max_range,
                                         @Positive double new_fuel_capacity,
                                         @Positive double new_cruising_speed) {}

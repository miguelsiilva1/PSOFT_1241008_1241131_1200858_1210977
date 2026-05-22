package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

public record RegisterAircraftRequest(@NotBlank String registration_number,
                                      @NotBlank String model_name,
                                      Date manufacturing_date,
                                      Date last_maintenance,
                                      @Positive int max_flight_hours_until_maintenance,
                                      @Positive int max_days_until_maintenance,
                                      @PositiveOrZero int economy_seats,
                                      @PositiveOrZero int premium_economy_seats,
                                      @PositiveOrZero int business_seats,
                                      @PositiveOrZero int first_seats,
                                      @NotBlank String status) {}

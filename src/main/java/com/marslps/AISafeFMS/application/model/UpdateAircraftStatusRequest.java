package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;

public record UpdateAircraftStatusRequest(@NotBlank String registration_number,
                                          @NotBlank String status) {}

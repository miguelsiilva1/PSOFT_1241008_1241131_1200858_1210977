package com.marslps.AISafeFMS.model.vo;

import jakarta.validation.constraints.NotBlank;

public record AirportService(@NotBlank String service) {
    public AirportService(@NotBlank String service) {
        this.service = service;
    }
}

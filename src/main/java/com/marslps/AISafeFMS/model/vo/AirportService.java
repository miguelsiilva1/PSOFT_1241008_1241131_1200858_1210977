package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record AirportService(@NotBlank String service) {
    public AirportService(@NotBlank String service) {
        this.service = service;
    }
}

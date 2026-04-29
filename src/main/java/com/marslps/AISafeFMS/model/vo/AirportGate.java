package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record AirportGate(@NotBlank String gate) {
    public AirportGate(@NotBlank String gate) {
        this.gate = gate;
    }
}

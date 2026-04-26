package com.marslps.AISafeFMS.model.vo;

import jakarta.validation.constraints.NotBlank;

public record AirportGate(@NotBlank String gate) {
    public AirportGate(@NotBlank String gate) {
        this.gate = gate;
    }
}

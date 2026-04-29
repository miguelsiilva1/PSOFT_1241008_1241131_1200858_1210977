package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record AirportTerminal(@NotBlank String terminal) {
    public AirportTerminal(@NotBlank String terminal) {
        this.terminal = terminal;
    }
}

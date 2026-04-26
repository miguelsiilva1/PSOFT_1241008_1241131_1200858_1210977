package com.marslps.AISafeFMS.model.vo;

import jakarta.validation.constraints.NotBlank;

public record AirportTerminal(@NotBlank String terminal) {
    public AirportTerminal(@NotBlank String terminal) {
        this.terminal = terminal;
    }
}

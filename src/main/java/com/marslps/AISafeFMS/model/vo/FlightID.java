package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record FlightID(@NotBlank String id) {
    public FlightID(@NotBlank String id) {
        this.id = id;
    }
}

package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.apache.axis.types.PositiveInteger;

import java.util.Set;

@Embeddable
public record AircraftModel(@NotBlank String model, PositiveInteger seating_capacity, @Positive double range, @NotBlank
                            Set<String> features) {
    public AircraftModel(@NotBlank String model, PositiveInteger seating_capacity, @Positive double range, @NotBlank
    Set<String> features) {
        this.model = model;
        this.seating_capacity = seating_capacity;
        this.range = range;
        this.features = features;
    }
}

package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record AircraftManufacturer(@NotBlank String manufacturer) {
    public AircraftManufacturer(@NotBlank String manufacturer) {
        this.manufacturer = manufacturer;
    }
}

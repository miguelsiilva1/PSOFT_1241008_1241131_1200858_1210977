package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record AirportType(@NotBlank String type) {
    public AirportType(@NotBlank String type) {
        String clean_type = type.trim();
        this.type = clean_type;
    }
}

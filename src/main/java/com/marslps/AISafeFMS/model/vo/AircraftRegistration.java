package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalAircraftRegistrationNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record AircraftRegistration(@NotBlank String registration_number) {
    public AircraftRegistration(@NotBlank String registration_number) {
        this.registration_number = registration_number;
    }
}

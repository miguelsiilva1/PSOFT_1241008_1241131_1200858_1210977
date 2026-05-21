package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalAircraftRegistrationNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record AircraftRegistration(@NotBlank String registration_number) {
    public AircraftRegistration(@NotBlank String registration_number) {
        String clean_registration_number = registration_number.trim();
        if(!clean_registration_number.matches("[A-Z0-9-]{5,10}")) {
            throw new IllegalAircraftRegistrationNumber("We're sorry, but the aircraft registration number must be 5-10 characters, with optional hyphens.");
        }
        this.registration_number = clean_registration_number;
    }
    protected AircraftRegistration() {this("something");}
}

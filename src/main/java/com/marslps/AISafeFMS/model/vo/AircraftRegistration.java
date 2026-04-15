package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalAircraftRegistrationNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AircraftRegistration {
    @Column(unique = true)
    private final String registration_number;

    public AircraftRegistration() {
        this.registration_number = "";
    }

    public AircraftRegistration(String registration_number) {
        if(registration_number == null || registration_number.trim().isEmpty()) {
            throw new EmptyStringException("We're sorry, but the aircraft registration number cannot be empty!");
        }
        String clean_registration_number = registration_number.trim();
        if(!clean_registration_number.matches("[A-Z0-9-]{5,10}")) {
            throw new IllegalAircraftRegistrationNumber("We're sorry, but the aircraft registration number must be 5-10 characters, with optional hyphens.");
        }
        this.registration_number = clean_registration_number;
    }
}

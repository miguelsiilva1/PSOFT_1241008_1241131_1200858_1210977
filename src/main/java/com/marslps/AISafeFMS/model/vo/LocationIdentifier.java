package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalIataCodeException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record LocationIdentifier(@NotBlank String iata_code) {
    public LocationIdentifier(@NotBlank String iata_code) {
        String clean_iata_code = iata_code.trim();
        if(!clean_iata_code.matches("[A-Z]{3}")) {
            throw new IllegalIataCodeException("We're sorry, but the IATA code must be 3 letters long.");
        }
        this.iata_code = iata_code;
    }
}

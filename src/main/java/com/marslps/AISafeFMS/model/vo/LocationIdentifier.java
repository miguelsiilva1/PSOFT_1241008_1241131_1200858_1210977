package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalIataCodeException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record LocationIdentifier(@NotBlank String iata) {
    public LocationIdentifier(@NotBlank String iata) {
        String clean_iata = iata.trim();
        if(!clean_iata.matches("[A-Z]{3}")) {
            throw new IllegalIataCodeException("We're sorry, but the IATA code must be 3 letters long.");
        }
        this.iata = clean_iata;
    }
    protected LocationIdentifier() {this("ZZZ");}
}

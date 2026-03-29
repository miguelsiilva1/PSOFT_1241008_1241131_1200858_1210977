package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalIataCodeException;

public class LocationIdentifier {
    private String iata_code;

    public LocationIdentifier(String iata_code) {
        if(iata_code == null || iata_code.trim().isEmpty()) {
            throw new EmptyStringException("We're sorry, but the airport's IATA code cannot be empty!");
        }
        String clean_iata_code = iata_code.trim();
        if(!clean_iata_code.matches("[A-Z]{3}")) {
            throw new IllegalIataCodeException("We're sorry, but the IATA code must be 3 letters long.");
        }
        this.iata_code = iata_code;
    }
}

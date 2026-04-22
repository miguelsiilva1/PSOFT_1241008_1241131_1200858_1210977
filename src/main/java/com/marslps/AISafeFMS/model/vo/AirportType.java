package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import jakarta.persistence.Column;

public class AirportType {
    @Column
    private final String type;

    public AirportType() {this.type = "";}

    public AirportType(String type) {
        if(type == null || type.trim().isEmpty()) {
            throw new EmptyStringException("Airport type cannot be empty!");
        }
        String clean_type = type.trim();
        this.type = clean_type;
    }
}

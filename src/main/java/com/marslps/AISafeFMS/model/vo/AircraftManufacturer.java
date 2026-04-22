package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AircraftManufacturer {
    @Column
    private final String manufacturer;

    public AircraftManufacturer() {
        this.manufacturer = "";
    }

    public AircraftManufacturer(String manufacturer) {
        if(manufacturer == null || manufacturer.trim().isEmpty()) {
            throw new EmptyStringException("Manufacturer name cannot be empty!");
        }
        String clean_manufacturer = manufacturer.trim();
        this.manufacturer = clean_manufacturer;
    }
}

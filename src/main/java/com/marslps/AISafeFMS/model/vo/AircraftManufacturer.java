package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AircraftManufacturer {
    @Column
    private final String manufacturer;

    public AircraftManufacturer() {
        this.manufacturer = "";
    }



}

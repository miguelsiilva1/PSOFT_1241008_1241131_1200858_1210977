package com.marslps.AISafeFMS.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class AircraftManufacturer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "aircraft_manu_db_id")
    private Long db_id;
    @Column @NotBlank
    private String manufacturer;
    public AircraftManufacturer(@NotBlank String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public AircraftManufacturer() {
        this.manufacturer = "something";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AircraftManufacturer manufacturer)) {return false;}
        return Objects.equals(this.manufacturer, manufacturer.manufacturer);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.manufacturer);
    }
}

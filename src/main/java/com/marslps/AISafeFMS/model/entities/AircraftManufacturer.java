package com.marslps.AISafeFMS.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class AircraftManufacturer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "aircraft_manu_db_id")
    private Long db_id;
    @Column @NotBlank
    private String name;

    @JsonCreator
    public AircraftManufacturer(@JsonProperty("name") @NotBlank String manufacturer) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer name cannot be empty");
        }
        this.name = manufacturer;
    }

    protected AircraftManufacturer() {}

    public String obtainName() {return this.name;}

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AircraftManufacturer manufacturer)) {return false;}
        return Objects.equals(this.name, manufacturer.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}

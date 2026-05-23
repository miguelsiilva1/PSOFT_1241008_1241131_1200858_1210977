package com.marslps.AISafeFMS.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.Set;

@Entity
public class AircraftModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank private String name;
    @Column
    @Positive private int seating_capacity;
    @Column
    @Positive private double max_range;
    @Positive private double fuel_capacity;
    @Positive private double cruising_speed;
    @ManyToOne
    private AircraftManufacturer manufacturer;
    @Column
    private Set<String> features;

    public AircraftModel() {}

    public AircraftModel(@NotEmpty String model,
                         @Positive int seating_capacity,
                         @Positive double range,
                         @Positive double fuel_capacity,
                         @Positive double cruising_speed,
                         AircraftManufacturer manufacturer,
                         Set<String> features) {
        this.name = model;
        this.seating_capacity = seating_capacity;
        this.max_range = range;
        this.fuel_capacity = fuel_capacity;
        this.cruising_speed = cruising_speed;
        this.manufacturer = manufacturer;
        this.features = features;
    }

    public String obtainName() {
        return this.name;
    }
    public AircraftManufacturer obtainManufacturer() {return this.manufacturer;}
}

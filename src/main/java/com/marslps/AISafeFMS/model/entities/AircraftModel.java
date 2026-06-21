package com.marslps.AISafeFMS.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Setter;

import java.util.Set;

@Entity
public class AircraftModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @Positive
    private int seating_capacity;

    @Column
    @Positive
    private double max_range;

    @Positive
    private double fuel_capacity;

    @Positive
    private double cruising_speed;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AircraftManufacturer manufacturer;

    @Column
    private Set<String> features;

    public AircraftModel() {
    }

    public AircraftModel(@NotEmpty String name,
                         @Positive int seating_capacity,
                         @Positive double max_range,
                         @Positive double fuel_capacity,
                         @Positive double cruising_speed,
                         AircraftManufacturer manufacturer,
                         Set<String> features) {
        if (seating_capacity <= 0) {
            throw new IllegalArgumentException("Seating capacity must be strictly positive");
        }

        if (max_range <= 0) {
            throw new IllegalArgumentException("Max range must be strictly positive");
        }

        if (fuel_capacity <= 0) {
            throw new IllegalArgumentException("Fuel capacity must be strictly positive");
        }

        if (cruising_speed <= 0) {
            throw new IllegalArgumentException("Cruising speed must be strictly positive");
        }

        this.name = name;
        this.seating_capacity = seating_capacity;
        this.max_range = max_range;
        this.fuel_capacity = fuel_capacity;
        this.cruising_speed = cruising_speed;
        this.manufacturer = manufacturer;
        this.features = features;
    }

    public String obtainName() {
        return this.name;
    }

    public AircraftManufacturer obtainManufacturer() {
        return this.manufacturer;
    }

    public double obtainRange() {
        return this.max_range;
    }

    public int obtainCapacity() {
        return this.seating_capacity;
    }

    public double obtainFuel() {
        return this.fuel_capacity;
    }

    public double obtainSpeed() {
        return this.cruising_speed;
    }
}
package com.marslps.AISafeFMS.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AircraftModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank String model;
    @Column
    @Positive int seating_capacity;
    @Column
    @Positive double range;
    @Column
    @NotBlank Set<String> features;

    public AircraftModel() {
        this.model = "something";
        this.seating_capacity = 1;
        this.range = 1.0;
        this.features = new HashSet<>(List.of("something"));
    }

    public AircraftModel(@NotBlank String model, @Positive int seating_capacity, @Positive double range, @NotBlank
    Set<String> features) {
        this.model = model;
        this.seating_capacity = seating_capacity;
        this.range = range;
        this.features = features;
    }
}

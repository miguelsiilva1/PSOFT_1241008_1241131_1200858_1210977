package com.marslps.AISafeFMS.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class AirportType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "airport_type_db_id")
    private Long db_id;
    @Column @NotBlank
    private String type;
    public AirportType(@NotBlank String type) {
        this.type = type;
    }

    public AirportType() {
        this.type = "something";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AirportType type)) {return false;}
        return Objects.equals(this.type, type.type);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.type);
    }
}

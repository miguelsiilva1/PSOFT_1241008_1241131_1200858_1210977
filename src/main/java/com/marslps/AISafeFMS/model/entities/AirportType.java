package com.marslps.AISafeFMS.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class AirportType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "airport_type_db_id")
    private Long db_id;
    @Column @NotBlank
    private String type;

    @JsonCreator
    public AirportType(@JsonProperty("type") @NotBlank String type) {
        this.type = type;
    }

    protected AirportType() {}

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

package com.marslps.AISafeFMS.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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
}

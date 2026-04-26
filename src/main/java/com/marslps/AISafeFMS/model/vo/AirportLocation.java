package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;

public record AirportLocation(@NotBlank String name, @NotBlank String city, @NotBlank String country) {
    public AirportLocation(@NotBlank String name, @NotBlank String city, @NotBlank String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }
}

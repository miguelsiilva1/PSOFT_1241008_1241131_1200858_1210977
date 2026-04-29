package com.marslps.AISafeFMS.model.vo;

import jakarta.validation.constraints.NotBlank;

public record AirportLocation(@NotBlank String city, @NotBlank String country, @NotBlank String region) {
    public AirportLocation(@NotBlank String city, @NotBlank String country, @NotBlank String region) {
        this.city = city;
        this.country = country;
        this.region = region;
    }
    public AirportLocation() {
        this("something", "something", "something");
    }
}

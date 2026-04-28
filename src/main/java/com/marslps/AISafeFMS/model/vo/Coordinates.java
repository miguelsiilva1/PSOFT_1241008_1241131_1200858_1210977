package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Embeddable
public record Coordinates(@Positive double longitude, @NotBlank char longitude_direction, @Positive double latitude, @NotBlank char latitude_direction) {
    public Coordinates(@Positive double longitude, @NotBlank char longitude_direction, @Positive double latitude, @NotBlank char latitude_direction) {
        this.longitude = longitude;
        this.longitude_direction = longitude_direction;
        this.latitude = latitude;
        this.latitude_direction = latitude_direction;
    }
}

package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

@Embeddable
public record Coordinates(@Positive double longitude, @Positive double longitude_direction, @Positive double latitude, @Positive double latitude_direction) {
    public Coordinates(@Positive double longitude, @Positive double longitude_direction, @Positive double latitude, @Positive double latitude_direction) {
        this.longitude = longitude;
        this.longitude_direction = longitude_direction;
        this.latitude = latitude;
        this.latitude_direction = latitude_direction;
    }
}

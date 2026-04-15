package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Coordinates {
    @Column
    private double longitude;
    @Column(length = 1)
    private char longitude_direction;
    @Column
    private double latitude;
    @Column(length = 1)
    private char latitude_direction;
}

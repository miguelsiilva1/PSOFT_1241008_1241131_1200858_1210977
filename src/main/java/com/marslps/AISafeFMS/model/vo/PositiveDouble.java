package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PositiveDouble {
    @Column
    private double number;

    public PositiveDouble() {
        this.number = 0.0;
    }

    public PositiveDouble(double number) {
        double correct_number = number;
        if(number < 0.0) {
            correct_number = Math.abs(number);
        }
        this.number = correct_number;
    }
}

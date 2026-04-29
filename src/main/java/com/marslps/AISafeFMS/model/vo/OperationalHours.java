package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

@Embeddable
public record OperationalHours(@Positive int opening_hours, @Positive int closing_hours) {
    public OperationalHours(@Positive int opening_hours, @Positive int closing_hours) {
        this.opening_hours = opening_hours;
        this.closing_hours = closing_hours;
    }
    public OperationalHours() {
        this(1, 1);
    }
}

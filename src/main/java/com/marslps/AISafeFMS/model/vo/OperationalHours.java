package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.apache.axis.types.PositiveInteger;

@Embeddable
public record OperationalHours(PositiveInteger opening_hours, PositiveInteger closing_hours) {
    public OperationalHours(PositiveInteger opening_hours, PositiveInteger closing_hours) {
        this.opening_hours = opening_hours;
        this.closing_hours = closing_hours;
    }
}

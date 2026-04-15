package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.apache.axis.types.PositiveInteger;

@Embeddable
public class OperationalHours {
    @Column
    private PositiveInteger opening_hours;
    @Column
    private PositiveInteger closing_hours;
}

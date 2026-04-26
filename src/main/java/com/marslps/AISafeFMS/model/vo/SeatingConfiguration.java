package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Embeddable;
import org.apache.axis.types.PositiveInteger;

@Embeddable
public record SeatingConfiguration(PositiveInteger economy_seats, PositiveInteger premium_economy_seats, PositiveInteger business_seats, PositiveInteger first_seats) {
    public SeatingConfiguration(PositiveInteger economy_seats, PositiveInteger premium_economy_seats, PositiveInteger business_seats, PositiveInteger first_seats) {
        this.economy_seats = economy_seats;
        this.premium_economy_seats = premium_economy_seats;
        this.business_seats = business_seats;
        this.first_seats = first_seats;
    }
}

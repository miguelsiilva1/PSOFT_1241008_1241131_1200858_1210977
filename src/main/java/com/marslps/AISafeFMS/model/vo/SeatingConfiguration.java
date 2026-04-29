package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

@Embeddable
public record SeatingConfiguration(@Positive int economy_seats, @Positive int premium_economy_seats, @Positive int business_seats, @Positive int first_seats) {
    public SeatingConfiguration(@Positive int economy_seats, @Positive int premium_economy_seats, @Positive int business_seats, @Positive int first_seats) {
        this.economy_seats = economy_seats;
        this.premium_economy_seats = premium_economy_seats;
        this.business_seats = business_seats;
        this.first_seats = first_seats;
    }
    public SeatingConfiguration() {
        this(1, 1, 1, 1);
    }
}

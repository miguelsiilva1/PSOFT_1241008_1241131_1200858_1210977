package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Embeddable
public record SeatingConfiguration(@PositiveOrZero int economy_seats, @PositiveOrZero int premium_economy_seats, @PositiveOrZero int business_seats, @PositiveOrZero int first_seats) {
    public SeatingConfiguration(@PositiveOrZero int economy_seats, @PositiveOrZero int premium_economy_seats, @PositiveOrZero int business_seats, @PositiveOrZero int first_seats) {
        if (economy_seats < 0 || premium_economy_seats < 0 ||
                business_seats < 0 || first_seats < 0) {
            throw new IllegalArgumentException("The number of seats cannot be negative.");
        }

        this.economy_seats = economy_seats;
        this.premium_economy_seats = premium_economy_seats;
        this.business_seats = business_seats;
        this.first_seats = first_seats;
    }
    protected SeatingConfiguration() {
        this(1, 1, 1, 1);
    }
}

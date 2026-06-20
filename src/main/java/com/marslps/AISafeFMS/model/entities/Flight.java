package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.FlightStatus;
import com.marslps.AISafeFMS.model.vo.FlightID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;
import java.util.Objects;

@Entity
public class Flight {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "flight_db_id")
    private Long db_id;
    @Column(unique = true)
    private FlightID id;
    @JoinColumn @ManyToOne
    private Route route;
    @ManyToOne
    private Aircraft aircraft;
    @Temporal(TemporalType.TIMESTAMP) @NotNull
    private Date scheduled_departure;
    @PositiveOrZero
    private double delayed_hours;
    @Transient
    private Date effective_departure;
    @Transient
    private Date scheduled_arrival;
    @Temporal(TemporalType.TIMESTAMP)
    private Date effective_arrival;
    @Enumerated
    private FlightStatus status;

    protected Flight() {}
    public Flight(FlightID id,
                  Route route,
                  Aircraft aircraft,
                  @NotNull Date scheduled_departure,
                  @PositiveOrZero double delayed_hours,
                  FlightStatus status) {
        this.id = id;
        this.route = route;
        this.aircraft = aircraft;
        this.scheduled_departure = scheduled_departure;
        this.delayed_hours = delayed_hours;
        this.effective_departure = new Date(scheduled_departure.getTime() + ((long) delayed_hours * 60 * 60 * 1000));
        this.scheduled_arrival = route.calculateScheduledArrival(scheduled_departure);
        this.effective_arrival = new Date(this.scheduled_arrival.getTime() + ((long) delayed_hours * 60 * 60 * 1000));
        this.status = status;
    }
    public FlightID obtainFlightID() {
        return this.id;
    }

    public Route obtainRoute() {
        return this.route;
    }

    public Aircraft obtainAircraft() {
        return this.aircraft;
    }

    public Date obtainScheduledDeparture() {
        return this.scheduled_departure;
    }

    public Date obtainScheduledArrival() {
        return this.scheduled_arrival;
    }

    public FlightStatus obtainStatus() {
        return this.status;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Flight flight)) {return false;}
        return Objects.equals(this.id, flight.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}

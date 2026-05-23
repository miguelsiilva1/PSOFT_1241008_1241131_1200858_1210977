package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.RouteID;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
public class Route {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "route_db_id")
    private Long db_id;
    @Embedded
    private RouteID id;
    @JoinColumn @ManyToOne
    private Airport departure;
    @JoinColumn @ManyToOne
    private Airport destination;
    @Positive
    private double flight_time;
    @Positive
    private double flight_distance;
    @Positive
    private double min_aircraft_range;
    @Positive
    private int min_aircraft_capacity;
    @Column
    private boolean active;
    @Version
    private Long version;

    protected Route() {}
    public Route(RouteID id,
                 Airport departure,
                 Airport destination,
                 @Positive double flight_time,
                 @Positive double flight_distance,
                 @Positive double min_aircraft_range,
                 @Positive int min_aircraft_capacity,
                 boolean active) {
        if (id == null) {
            throw new IllegalArgumentException("Route ID is required.");
        }

        if (departure == null) {
            throw new IllegalArgumentException("Departure airport is required.");
        }

        if (destination == null) {
            throw new IllegalArgumentException("Destination airport is required.");
        }

        if (departure.equals(destination)) {
            throw new IllegalArgumentException("Departure and destination airports must be different.");
        }
        if (flight_time <= 0) {
            throw new IllegalArgumentException("Flight time must be positive.");
        }

        if (flight_distance <= 0) {
            throw new IllegalArgumentException("Flight distance must be positive.");
        }

        if (min_aircraft_range <= 0) {
            throw new IllegalArgumentException("Minimum aircraft range must be positive.");
        }

        if (min_aircraft_capacity <= 0) {
            throw new IllegalArgumentException("Minimum aircraft capacity must be positive.");
        }

        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.flight_time = flight_time;
        this.flight_distance = flight_distance;
        this.min_aircraft_range = min_aircraft_range;
        this.min_aircraft_capacity = min_aircraft_capacity;
        this.active = active;
    }
    public void updateRoute(double flight_time,
                            double flight_distance,
                            double min_aircraft_range,
                            int min_aircraft_capacity) {

        if (flight_time <= 0) {
            throw new IllegalArgumentException("Flight time must be positive.");
        }

        if (flight_distance <= 0) {
            throw new IllegalArgumentException("Flight distance must be positive.");
        }

        if (min_aircraft_range <= 0) {
            throw new IllegalArgumentException("Minimum aircraft range must be positive.");
        }

        if (min_aircraft_capacity <= 0) {
            throw new IllegalArgumentException("Minimum aircraft capacity must be positive.");
        }

        this.flight_time = flight_time;
        this.flight_distance = flight_distance;
        this.min_aircraft_range = min_aircraft_range;
        this.min_aircraft_capacity = min_aircraft_capacity;
    }

    public void deactivate() {
        if (!this.active) {
            throw new IllegalArgumentException("Route is already inactive.");
        }

        this.active = false;
    }

    public RouteID obtainRouteID() {
        return this.id;
    }

    public Airport obtainDeparture() {
        return this.departure;
    }

    public Airport obtainDestination() {
        return this.destination;
    }

    public boolean isActive() {
        return this.active;
    }

    public Date calculateScheduledArrival(Date scheduled_departure) {
        return new Date(scheduled_departure.getTime() + ((long) this.flight_time * 60 * 60 * 1000));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Route route)) {return false;}
        return Objects.equals(this.id, route.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}

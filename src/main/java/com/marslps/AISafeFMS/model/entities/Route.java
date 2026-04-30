package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import com.marslps.AISafeFMS.model.vo.RouteID;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.Date;

@Entity
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
    private double min_aircraft_capacity;
    @Column
    private boolean active;

    public Route() {
        this.id = new RouteID();
        this.departure = new Airport();
        this.destination = new Airport();
        this.flight_time = 0.0;
        this.flight_distance = 0.0;
        this.min_aircraft_range = 0.0;
        this.min_aircraft_capacity = 0.0;
        this.active = true;
    }
    public Route(RouteID id,
                 Airport departure,
                 Airport destination,
                 @Positive double flight_time,
                 @Positive double flight_distance,
                 @Positive double min_aircraft_range,
                 @Positive double min_aircraft_capacity,
                 boolean active) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.flight_time = flight_time;
        this.flight_distance = flight_distance;
        this.min_aircraft_range = min_aircraft_range;
        this.min_aircraft_capacity = min_aircraft_capacity;
        this.active = active;
    }

    public Date calculateScheduledArrival(Date scheduled_departure) {
        return new Date(scheduled_departure.getTime() + ((long) this.flight_time * 60 * 60 * 1000));
    }
}

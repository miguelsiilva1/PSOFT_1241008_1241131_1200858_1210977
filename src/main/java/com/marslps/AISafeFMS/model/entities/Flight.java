package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.FlightStatus;
import com.marslps.AISafeFMS.model.vo.FlightID;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date scheduled_departure;
    @Positive
    private double delayed_hours;
    @Transient
    private Date effective_departure;
    @Transient
    private Date scheduled_arrival;
    @Temporal(TemporalType.DATE)
    private Date effective_arrival;
    @Enumerated
    private FlightStatus status;

    public Flight() {
        this.id = new FlightID();
        this.route = new Route();
        this.aircraft = new Aircraft();
        this.scheduled_departure = new Date();
        this.delayed_hours = 0.0;
        this.effective_departure = new Date();
        this.scheduled_arrival = new Date();
        this.effective_arrival = new Date();
        this.status = FlightStatus.SCHEDULED;
    }
    public Flight(FlightID id,
                  Route route,
                  Aircraft aircraft,
                  Date scheduled_departure,
                  @Positive double delayed_hours) {
        this.id = id;
        this.route = route;
        this.aircraft = aircraft;
        this.scheduled_departure = scheduled_departure;
        this.delayed_hours = delayed_hours;
        this.effective_departure = new Date(scheduled_departure.getTime() + ((long) delayed_hours * 60 * 60 * 1000));
        // mudar depois, o atributo é private
        // this.scheduled_arrival = route.flight_time;
        // this.effective_arrival = new Date(route.flight_time.getTime + ((long) delayed_hours * 60 * 60 * 1000));
    }
}

package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import com.marslps.AISafeFMS.model.vo.RouteID;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

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
}

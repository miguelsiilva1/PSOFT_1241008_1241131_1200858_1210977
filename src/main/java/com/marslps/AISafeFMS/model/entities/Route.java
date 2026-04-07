package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import com.marslps.AISafeFMS.model.vo.RouteID;
import jakarta.persistence.*;

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
    @Embedded
    private PositiveDouble flight_time;
    @Embedded
    private PositiveDouble flight_distance;
    @Embedded
    private PositiveDouble min_aircraft_range;
    @Embedded
    private PositiveDouble min_aircraft_capacity;
    @Column
    private boolean active;
}

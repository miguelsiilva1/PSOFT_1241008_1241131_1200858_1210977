package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.FlightStatus;
import com.marslps.AISafeFMS.model.vo.FlightID;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Flight {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "flight_db_id")
    private Long db_id;
    @Column(unique = true)
    private FlightID id;
    @JoinColumn @ManyToOne
    private Route route;
    @Temporal(TemporalType.DATE)
    private Date scheduled_datetime;
    @Embedded
    private PositiveDouble delayed_hours;
    @ManyToOne
    private Aircraft aircraft;
    @Enumerated
    private FlightStatus status;
}

package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import jakarta.persistence.*;
import org.apache.axis.types.PositiveInteger;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Set;

@Entity
public class Aircraft {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "aircraft_db_id")
    private Long db_id;
    @Embedded
    private AircraftRegistration registration_number;
    @Embedded
    private NonEmptyString manufactor;
    @Embedded
    private NonEmptyString model;
    @Column
    private PositiveInteger number_of_seats;
    @Embedded
    private PositiveDouble fuel_capacity;
    @Embedded
    private PositiveDouble max_range;
    @Embedded
    private PositiveDouble cruising_speed;
    @Temporal(TemporalType.DATE)
    private Date manufactoring_date;
    @Embedded
    private BufferedImage image;
    @Temporal(TemporalType.DATE)
    private Date last_maintenance;
    @Transient
    private PositiveInteger days_since_last_maintenance;
    @Column
    private PositiveInteger max_flight_hours_until_maintenance;
    @Column
    private PositiveInteger max_days_until_maintenance;
    @Column
    private AircraftStatus status;
    @ElementCollection
    private Set<NonEmptyString> features;

}

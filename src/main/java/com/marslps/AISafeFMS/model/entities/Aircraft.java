package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftManufacturer;
import com.marslps.AISafeFMS.model.vo.AircraftModel;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.SeatingConfiguration;
import jakarta.persistence.*;
import org.apache.axis.types.PositiveInteger;
import java.awt.image.BufferedImage;
import java.util.Date;

@Entity
public class Aircraft {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "aircraft_db_id")
    private Long db_id;
    @Embedded @Column(unique = true)
    private AircraftRegistration registration_number;
    @Embedded
    private AircraftManufacturer manufacturer;
    @Embedded
    private AircraftModel model;
    @Embedded
    private SeatingConfiguration seating_configuration;
    @Column
    private double fuel_capacity;
    @Column
    private double cruising_speed;
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

}

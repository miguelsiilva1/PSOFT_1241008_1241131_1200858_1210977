package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftModel;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.SeatingConfiguration;
import jakarta.persistence.*;
import org.apache.axis.types.PositiveInteger;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Set;

@Entity
public class Aircraft {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "aircraft_db_id")
    private Long db_id;
    @Embedded @Column(unique = true)
    private AircraftRegistration registration_number;
    @JoinColumn @ManyToOne
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

    public Aircraft() {
        this.registration_number = new AircraftRegistration("1");
        this.manufacturer = new AircraftManufacturer("");
        this.model = new AircraftModel("something", new PositiveInteger("1"), 100.1, Set.of("something"));
        this.seating_configuration = new SeatingConfiguration(new PositiveInteger("100"), new PositiveInteger("50"), new PositiveInteger("40"), new PositiveInteger("20"));
        this.fuel_capacity = 250.0;
        this.cruising_speed = 900.0;
        this.manufactoring_date = new Date("");
        this.image = null;
        this.last_maintenance = new Date("");
        this.days_since_last_maintenance = new PositiveInteger("1");
        this.max_flight_hours_until_maintenance = new PositiveInteger("1");
        this.max_days_until_maintenance = new PositiveInteger("1");
        this.status = AircraftStatus.AVAILABLE;
    }
}

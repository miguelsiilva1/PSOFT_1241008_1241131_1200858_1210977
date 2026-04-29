package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftModel;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.SeatingConfiguration;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
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
    private int days_since_last_maintenance;
    @Column @Positive
    private int max_flight_hours_until_maintenance;
    @Column @Positive
    private int max_days_until_maintenance;
    @Column
    private AircraftStatus status;

    public Aircraft() {
        this.registration_number = new AircraftRegistration("1");
        this.manufacturer = new AircraftManufacturer("");
        this.model = new AircraftModel("something", 1, 100.1, Set.of("something"));
        this.seating_configuration = new SeatingConfiguration(100, 50, 40, 20);
        this.fuel_capacity = 250.0;
        this.cruising_speed = 900.0;
        this.manufactoring_date = new Date("");
        this.image = null;
        this.last_maintenance = new Date("");
        this.days_since_last_maintenance = 1;
        this.max_flight_hours_until_maintenance = 1;
        this.max_days_until_maintenance = 1;
        this.status = AircraftStatus.AVAILABLE;
    }

    public Aircraft(AircraftRegistration registration_number,
                    AircraftManufacturer manufacturer,
                    AircraftModel model,
                    SeatingConfiguration seating_configuration,
                    @Positive double fuel_capacity,
                    @Positive double cruising_speed,
                    Date manufacturing_date,
                    BufferedImage image,
                    Date last_maintenance,
                    @Positive int max_flight_hours_until_maintenance,
                    @Positive int max_days_until_maintenance,
                    AircraftStatus status) {
        this.registration_number = registration_number;
        this.manufacturer = manufacturer;
        this.model = model;
        this.seating_configuration = seating_configuration;
        this.fuel_capacity = fuel_capacity;
        this.cruising_speed = cruising_speed;
        this.manufactoring_date = manufacturing_date;
        this.image = image;
        this.last_maintenance = last_maintenance;
        this.days_since_last_maintenance = max_days_until_maintenance;
        this.max_flight_hours_until_maintenance = max_flight_hours_until_maintenance;
        this.max_days_until_maintenance = max_days_until_maintenance;
        this.status = status;
    }
}

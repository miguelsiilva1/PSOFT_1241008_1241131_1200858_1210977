package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.SeatingConfiguration;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Aircraft {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "aircraft_db_id")
    private Long db_id;
    @Embedded @Column(unique = true)
    private AircraftRegistration registration_number;
    @Setter
    @JoinColumn @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AircraftModel model;
    @Embedded
    private SeatingConfiguration seating_configuration;
    @Temporal(TemporalType.DATE)
    private Date manufactoring_date;
    @Column
    private String image;
    @Column
    private double operational_hours;
    @Column
    private double total_flight_hours;
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
    @Version
    private Long version;

    protected Aircraft() {}

    public Aircraft(AircraftRegistration registration_number,
                    AircraftModel model,
                    SeatingConfiguration seating_configuration,
                    Date manufacturing_date,
                    Date last_maintenance,
                    String image,
                    double operational_hours,
                    double total_flight_hours,
                    @Positive int max_flight_hours_until_maintenance,
                    @Positive int max_days_until_maintenance,
                    AircraftStatus status) {
        if (max_flight_hours_until_maintenance <= 0) {
            throw new IllegalArgumentException("Max flight hours until maintenance must be strictly positive");
        }

        if (max_days_until_maintenance <= 0) {
            throw new IllegalArgumentException("Max days until maintenance must be strictly positive");
        }

        this.registration_number = registration_number;
        this.model = model;
        this.seating_configuration = seating_configuration;
        this.manufactoring_date = manufacturing_date;
        this.image = image;
        this.operational_hours = operational_hours;
        this.total_flight_hours = total_flight_hours;
        this.last_maintenance = last_maintenance;
        this.days_since_last_maintenance = max_days_until_maintenance;
        this.max_flight_hours_until_maintenance = max_flight_hours_until_maintenance;
        this.max_days_until_maintenance = max_days_until_maintenance;
        this.status = status;
    }

    public String obtainRegistrationNumber() {
        return this.registration_number.registration_number();
    }
    public String obtainManufacturerName() {return this.model.obtainManufacturer().obtainName();}

    public String obtainModelName() {return this.model.obtainName();}

    public int obtainEconomySeats() {return this.seating_configuration.economy_seats();}
    public int obtainPremiumEconomySeats() {return this.seating_configuration.premium_economy_seats();}
    public int obtainBusinessSeats() {return this.seating_configuration.business_seats();}
    public int obtainFirstSeats() {return this.seating_configuration.first_seats();}
    public Date obtainManufacturingDate() { return this.manufactoring_date; }
    public Date obtainLastMaintenance() { return this.last_maintenance; }
    public int obtainDaysSinceLastMaintenance() { return this.days_since_last_maintenance; }
    public int obtainMaxFlightHoursUntilMaintenance() { return this.max_flight_hours_until_maintenance; }
    public int obtainMaxDaysUntilMaintenance() { return this.max_days_until_maintenance; }
    public String obtainAircraftStatus() {return this.status.toString();}
    public String obtainImage() { return this.image; }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Aircraft aircraft)) {return false;}
        return Objects.equals(this.registration_number, aircraft.registration_number);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.registration_number);
    }
    public void changeStatus(AircraftStatus newStatus) {
        this.status = newStatus;
    }

    public double obtainRange() {
        return model.obtainRange();
    }

    public int obtainCapacity() {
        return model.obtainCapacity();
    }

    public double obtainOperationalHours() {return this.operational_hours;}
    public double obtainFlightHours() {return this.total_flight_hours;}
    public AircraftModel obtainModel() {return this.model;}

}

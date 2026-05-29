package com.marslps.AISafeFMS.application.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
public class AircraftDTO extends RepresentationModel<AircraftDTO> {
    private String registration_number;
    private String manufacturer;
    private String model;
    private int economy_seats;
    private int premium_economy_seats;
    private int business_seats;
    private int first_seats;
    private Date manufactoring_date;
    private Date last_maintenance;
    private int days_since_last_maintenance;
    private int max_flight_hours_until_maintenance;
    private int max_days_until_maintenance;
    private String status;
    private String image;

    public AircraftDTO(Link initialLink, String registration_number, String manufacturer, String model, int economy_seats, int premium_economy_seats, int business_seats, int first_seats, Date manufactoring_date, Date last_maintenance, int days_since_last_maintenance, int max_flight_hours_until_maintenance, int max_days_until_maintenance, String status, String image) {
        super(initialLink);
        this.registration_number = registration_number;
        this.manufacturer = manufacturer;
        this.model = model;
        this.economy_seats = economy_seats;
        this.premium_economy_seats = premium_economy_seats;
        this.business_seats = business_seats;
        this.first_seats = first_seats;
        this.manufactoring_date = manufactoring_date;
        this.last_maintenance = last_maintenance;
        this.days_since_last_maintenance = days_since_last_maintenance;
        this.max_flight_hours_until_maintenance = max_flight_hours_until_maintenance;
        this.max_days_until_maintenance = max_days_until_maintenance;
        this.status = status;
        this.image = image;
    }

    public AircraftDTO(Iterable<Link> initialLinks, String registration_number, String manufacturer, String model, int economy_seats, int premium_economy_seats, int business_seats, int first_seats, Date manufactoring_date, Date last_maintenance, int days_since_last_maintenance, int max_flight_hours_until_maintenance, int max_days_until_maintenance, String status, String image) {
        super(initialLinks);
        this.registration_number = registration_number;
        this.manufacturer = manufacturer;
        this.model = model;
        this.economy_seats = economy_seats;
        this.premium_economy_seats = premium_economy_seats;
        this.business_seats = business_seats;
        this.first_seats = first_seats;
        this.manufactoring_date = manufactoring_date;
        this.last_maintenance = last_maintenance;
        this.days_since_last_maintenance = days_since_last_maintenance;
        this.max_flight_hours_until_maintenance = max_flight_hours_until_maintenance;
        this.max_days_until_maintenance = max_days_until_maintenance;
        this.status = status;
        this.image = image;
    }

    public AircraftDTO(String registration_number, String manufacturer, String model, int economy_seats, int premium_economy_seats, int business_seats, int first_seats, Date manufactoring_date, Date last_maintenance, int days_since_last_maintenance, int max_flight_hours_until_maintenance, int max_days_until_maintenance, String status, String image) {
        this.registration_number = registration_number;
        this.manufacturer = manufacturer;
        this.model = model;
        this.economy_seats = economy_seats;
        this.premium_economy_seats = premium_economy_seats;
        this.business_seats = business_seats;
        this.first_seats = first_seats;
        this.manufactoring_date = manufactoring_date;
        this.last_maintenance = last_maintenance;
        this.days_since_last_maintenance = days_since_last_maintenance;
        this.max_flight_hours_until_maintenance = max_flight_hours_until_maintenance;
        this.max_days_until_maintenance = max_days_until_maintenance;
        this.status = status;
        this.image = image;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AircraftDTO dto)) {return false;}
        return Objects.equals(this.registration_number, dto.registration_number);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.registration_number);
    }
}

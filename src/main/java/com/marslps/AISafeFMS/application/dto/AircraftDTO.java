package com.marslps.AISafeFMS.application.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

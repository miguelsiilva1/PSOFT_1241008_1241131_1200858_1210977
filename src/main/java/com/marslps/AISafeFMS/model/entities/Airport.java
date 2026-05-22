package com.marslps.AISafeFMS.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.*;

@Entity
@Getter
public class Airport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "airport_db_id")
    @JsonIgnore
    private Long id;
    @Column
    private String name;
    @Embedded
    private LocationIdentifier iata;
    @Embedded
    private Coordinates coordinates;
    @Embedded
    private AirportLocation airport_location;
    @JoinColumn @ManyToOne
    private AirportType airport_type;
    @ManyToMany
    @JsonIgnoreProperties({"id", "seating_capacity", "max_range", "fuel_capacity", "cruising_speed", "manufacturer", "features"})
    private Set<AircraftModel> certified_models;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<RunwayInfo> runway_info;
    @Column
    private AirportStatus status;
    @JoinColumn @ManyToOne
    private TimeZone time_zone;
    @Embedded
    private OperationalHours operational_hours;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<ContactInfo> contact_info;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<AirportTerminal> terminals;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<AirportGate> gates;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<AirportService> services;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> images;

    protected Airport() {}

    public Airport(String name,
                   LocationIdentifier iata,
                   Coordinates coordinates,
                   AirportLocation airport_location,
                   AirportType airport_type,
                   Set<AircraftModel> certified_models,
                   List<RunwayInfo> runway_info,
                   AirportStatus status,
                   TimeZone time_zone,
                   OperationalHours operational_hours,
                   List<ContactInfo> contact_info,
                   List<AirportTerminal> terminals,
                   List<AirportGate> gates,
                   List<AirportService> services,
                   List<String> images) {
        this.name = name;
        this.iata = iata;
        this.coordinates = coordinates;
        this.airport_location = airport_location;
        this.airport_type = airport_type;
        this.certified_models = certified_models;
        this.runway_info = runway_info;
        this.status = status;
        this.time_zone = time_zone;
        this.operational_hours = operational_hours;
        this.contact_info = contact_info;
        this.terminals = terminals;
        this.gates = gates;
        this.services = services;
        this.images = images;
    }

    public LocationIdentifier obtainIata() {
        return this.iata;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Airport airport)) {return false;}
        return Objects.equals(this.iata, airport.iata);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.iata);
    }

    public void addCertifiedAircraftModel(AircraftModel model) {
        if (this.certified_models == null) {
            this.certified_models = new HashSet<>();
        }
        if (this.certified_models.contains(model)) {
            throw new IllegalArgumentException("This Aircraft Model is already certified for this Airport.");
        }
        this.certified_models.add(model);
    }

    public void updateStatus(AirportStatus status) {
        if (this.status == status) {
            throw new IllegalArgumentException("The airport is already in the requested status.");
        }
        this.status = status;
    }
}

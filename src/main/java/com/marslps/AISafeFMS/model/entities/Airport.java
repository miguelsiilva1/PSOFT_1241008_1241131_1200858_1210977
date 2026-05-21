package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.*;

@Entity
public class Airport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "airport_db_id")
    private Long db_id;
    @Embedded
    private LocationIdentifier iata_code;
    @Embedded
    private Coordinates coordinates;
    @Embedded
    private AirportLocation airport_location;
    @JoinColumn @ManyToOne
    private AirportType airport_type;
    @OneToMany
    private Set<Aircraft> certified_aircrafts;
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
    private List<String> images;

    protected Airport() {}

    public Airport(LocationIdentifier iata_code,
                   Coordinates coordinates,
                   AirportLocation airport_location,
                   AirportType airport_type,
                   Set<Aircraft> certified_aircrafts,
                   List<RunwayInfo> runway_info,
                   TimeZone time_zone,
                   OperationalHours operational_hours,
                   List<ContactInfo> contact_info,
                   List<AirportTerminal> terminals,
                   List<AirportGate> gates,
                   List<AirportService> services,
                   List<String> images) {
        this.iata_code = iata_code;
        this.coordinates = coordinates;
        this.airport_location = airport_location;
        this.airport_type = airport_type;
        this.certified_aircrafts = certified_aircrafts;
        this.runway_info = runway_info;
        this.time_zone = time_zone;
        this.operational_hours = operational_hours;
        this.contact_info = contact_info;
        this.terminals = terminals;
        this.gates = gates;
        this.services = services;
        this.images = images;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Airport airport)) {return false;}
        return Objects.equals(this.iata_code, airport.iata_code);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.iata_code);
    }
}

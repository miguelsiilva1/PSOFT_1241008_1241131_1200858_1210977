package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @ElementCollection
    private List<AirportTerminal> terminals;
    @ElementCollection
    private List<AirportGate> gates;
    @ElementCollection
    private List<AirportService> services;
    @ElementCollection(fetch = FetchType.LAZY) @Column(name = "images_base64", columnDefinition = "TEXT")
    private List<String> images;

    public Airport() {
        this.iata_code = new LocationIdentifier("AAA");
        this.coordinates = new Coordinates(0, 'W',0, 'N');
        this.airport_location = new AirportLocation();
        this.airport_type = new AirportType();
        this.certified_aircrafts = new HashSet<>();
        this.runway_info = new ArrayList<>();
        this.time_zone =  new TimeZone();
        this.operational_hours = new OperationalHours();
        this.contact_info = new ArrayList<>();
        this.terminals = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.services = new ArrayList<>();
        this.images = new ArrayList<>();
    }
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
}

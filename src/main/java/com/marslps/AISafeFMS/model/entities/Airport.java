package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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
    @JoinColumn @ManyToOne
    private AirportType airport_type;
    @OneToMany
    private List<RunwayInfo> runway_info;
    @NotBlank
    private String region;
    @ElementCollection(fetch = FetchType.LAZY) @Column(name = "images_base64", columnDefinition = "TEXT")
    private List<String> images;
    @JoinColumn @ManyToOne
    private TimeZone time_zone;
    @Embedded
    private OperationalHours operational_hours;
    @Embedded
    private ContactInfo contact_info;
    @Column
    private AirportStatus status;
    @OneToMany
    private Set<Aircraft> aircrafts;
    @ElementCollection
    private Set<NonEmptyString> terminals;
    @ElementCollection
    private Set<NonEmptyString> gates;
    @ElementCollection
    private Set<NonEmptyString> services;

    public Aircraft() {
        this.iata_code = new LocationIdentifier("AAA");
        this.coordinates = new Coordinates(0, 'W',0, 'N');
        this.airport_type = new AirportType();
        // por acabar
    }
}

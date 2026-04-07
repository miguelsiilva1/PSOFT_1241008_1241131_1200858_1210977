package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;
import jakarta.persistence.*;

import java.awt.image.BufferedImage;
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
    @OneToMany
    private List<RunwayInfo> runway_info;
    @Embedded
    private NonEmptyString name;
    @Embedded
    private NonEmptyString city;
    @Embedded
    private NonEmptyString country;
    @Embedded
    private NonEmptyString region;
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
    @OneToMany // não será que é @ManyToMany
    private Set<Aircraft> aircrafts;
    @ElementCollection
    private Set<NonEmptyString> terminals;
    @ElementCollection
    private Set<NonEmptyString> gates;
    @ElementCollection
    private Set<NonEmptyString> services;
}

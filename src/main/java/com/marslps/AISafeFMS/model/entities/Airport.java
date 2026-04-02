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
    @ElementCollection
    private List<RunwayInfo> runway_info;
    @Embedded
    private NonEmptyString name;
    @Embedded
    private NonEmptyString city;
    @Embedded
    private NonEmptyString country;
    @Embedded
    private NonEmptyString region;
    @ElementCollection
    private List<BufferedImage> images;
    @Embedded
    private TimeZone time_zone;
    @Embedded
    private OperationalHours operational_hours;
    @Embedded
    private ContactInfo contact_info;
    @Column
    private AirportStatus status;
    @ElementCollection
    private Set<Aircraft> aircrafts;
    @ElementCollection
    private Set<NonEmptyString> terminals;
    @ElementCollection
    private Set<NonEmptyString> gates;
    @ElementCollection
    private Set<NonEmptyString> services;
}

package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

public class Airport {
    private LocationIdentifier iata_code;
    private Coordinates coordinates;
    private List<RunwayInfo> runway_info;
    private NonEmptyString name;
    private NonEmptyString city;
    private NonEmptyString country;
    private List<BufferedImage> images;
    private TimeZone time_zone;
    private OperationalHours operational_hours;
    private ContactInfo contact_info;
    private AirportStatus status;
    private Set<Aircraft> aircrafts;
    private Set<NonEmptyString> terminals;
    private Set<NonEmptyString> gates;
    private Set<NonEmptyString> services;
}

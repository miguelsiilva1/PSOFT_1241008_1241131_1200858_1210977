package com.marslps.AISafeFMS.application.dto;

import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.entities.AirportType;
import com.marslps.AISafeFMS.model.entities.TimeZone;
import com.marslps.AISafeFMS.model.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data

public class AirportDTO extends RepresentationModel<AirportDTO> {
    private String name;

    public AirportDTO(String name, LocationIdentifier iata, Coordinates coordinates, AirportLocation airport_location, AirportType airport_type, Set<AircraftModel> certified_models, List<RunwayInfo> runway_info, AirportStatus status, TimeZone time_zone, OperationalHours operational_hours, List<ContactInfo> contact_info, List<AirportTerminal> terminals, List<AirportGate> gates, List<AirportService> services, List<String> images) {
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

    public AirportDTO(Link initialLink, String name, LocationIdentifier iata, Coordinates coordinates, AirportLocation airport_location, AirportType airport_type, Set<AircraftModel> certified_models, List<RunwayInfo> runway_info, AirportStatus status, TimeZone time_zone, OperationalHours operational_hours, List<ContactInfo> contact_info, List<AirportTerminal> terminals, List<AirportGate> gates, List<AirportService> services, List<String> images) {
        super(initialLink);
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

    public AirportDTO(Iterable<Link> initialLinks, String name, LocationIdentifier iata, Coordinates coordinates, AirportLocation airport_location, AirportType airport_type, Set<AircraftModel> certified_models, List<RunwayInfo> runway_info, AirportStatus status, TimeZone time_zone, OperationalHours operational_hours, List<ContactInfo> contact_info, List<AirportTerminal> terminals, List<AirportGate> gates, List<AirportService> services, List<String> images) {
        super(initialLinks);
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

    private LocationIdentifier iata;
    private Coordinates coordinates;
    private AirportLocation airport_location;
    private AirportType airport_type;
    private Set<AircraftModel> certified_models;
    private List<RunwayInfo> runway_info;
    private AirportStatus status;
    private TimeZone time_zone;
    private OperationalHours operational_hours;
    private List<ContactInfo> contact_info;
    private List<AirportTerminal> terminals;
    private List<AirportGate> gates;
    private List<AirportService> services;
    private List<String> images;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AirportDTO dto)) {return false;}
        return Objects.equals(this.iata, dto.iata);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.iata);
    }
}
package com.marslps.AISafeFMS.application.dto;

import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.entities.AirportType;
import com.marslps.AISafeFMS.model.entities.TimeZone;
import com.marslps.AISafeFMS.model.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportDTO extends RepresentationModel<AirportDTO> {
    private String name;
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
package com.marslps.AISafeFMS.application.model;

import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterAirportRequest(
        @NotBlank String name,
        @NotNull LocationIdentifier iata,
        @NotNull Coordinates coordinates,
        @NotNull AirportLocation airport_location,
        @NotBlank String airport_type,
        List<RunwayInfo> runway_info,
        @NotNull AirportStatus status,
        @NotBlank String time_zone,
        @NotNull OperationalHours operational_hours,
        List<ContactInfo> contact_info,
        List<AirportTerminal> terminals,
        List<AirportGate> gates,
        List<AirportService> services,
        List<String> images
) {}

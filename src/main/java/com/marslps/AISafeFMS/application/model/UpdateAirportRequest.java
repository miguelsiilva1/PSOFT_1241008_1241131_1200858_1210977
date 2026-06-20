package com.marslps.AISafeFMS.application.model;

import com.marslps.AISafeFMS.model.vo.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateAirportRequest(
        @NotBlank String name,
        @NotNull Coordinates coordinates,
        @NotNull AirportLocation airport_location,
        @NotBlank String airport_type,
        @NotBlank String time_zone,
        @NotNull OperationalHours operational_hours,
        @NotNull List<RunwayInfo> runway_info,
        @NotNull List<ContactInfo> contact_info,
        @NotNull List<AirportTerminal> terminals,
        @NotNull List<AirportGate> gates,
        @NotNull List<AirportService> services,
        @NotNull List<String> images
) {}
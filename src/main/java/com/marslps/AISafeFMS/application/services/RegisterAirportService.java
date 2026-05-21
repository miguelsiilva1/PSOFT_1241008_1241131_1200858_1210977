package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.AirportType;
import com.marslps.AISafeFMS.model.entities.TimeZone;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;

import java.util.List;

public interface RegisterAirportService {
    Airport register(String name, LocationIdentifier iata, Coordinates coordinates, AirportLocation airport_location, AirportType airport_type, List<RunwayInfo> runway_info, AirportStatus status, TimeZone time_zone, OperationalHours operational_hours, List<ContactInfo> contact_info, List<AirportTerminal> terminals, List<AirportGate> gates, List<AirportService> services, List<String> images);
}
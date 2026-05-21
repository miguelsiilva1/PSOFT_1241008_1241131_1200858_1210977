package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.application.services.RegisterAirportService;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.AirportType;
import com.marslps.AISafeFMS.model.entities.TimeZone;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;
import com.marslps.AISafeFMS.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashSet;

@Service
public class RegisterAirportServiceImpl implements RegisterAirportService {
    private final AirportRepository airport_repo;

    public RegisterAirportServiceImpl(AirportRepository airport_repo) {
        this.airport_repo = airport_repo;
    }

    @Override
    public Airport register(String name, LocationIdentifier iata, Coordinates coordinates, AirportLocation airport_location, AirportType airport_type, List<RunwayInfo> runway_info, AirportStatus status, TimeZone time_zone, OperationalHours operational_hours, List<ContactInfo> contact_info, List<AirportTerminal> terminals, List<AirportGate> gates, List<AirportService> services, List<String> images) {
        if(airport_repo.findByIata(iata).isEmpty()) {
            return new Airport(name, iata, coordinates, airport_location, airport_type, new HashSet<>(), runway_info, status, time_zone, operational_hours, contact_info, terminals, gates, services, images);
        }
        throw new IllegalArgumentException("This Airport already exists.");
    }
}
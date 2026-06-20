package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.AirportType;
import com.marslps.AISafeFMS.model.entities.TimeZone;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateAirportServiceImpl implements UpdateAirportService {
    @Override
    public Airport updateStatus(Airport airport, AirportStatus status) {
        airport.updateStatus(status);
        return airport;
    }

    @Override
    public Airport update(Airport airport, String name, Coordinates coordinates, AirportLocation airport_location,
                          AirportType airport_type, TimeZone time_zone, OperationalHours operational_hours,
                          List<RunwayInfo> runway_info, List<ContactInfo> contact_info,
                          List<AirportTerminal> terminals, List<AirportGate> gates,
                          List<AirportService> services, List<String> images) {
        airport.update(name, coordinates, airport_location, airport_type, time_zone, operational_hours,
                runway_info, contact_info, terminals, gates, services, images);
        return airport;
    }
}
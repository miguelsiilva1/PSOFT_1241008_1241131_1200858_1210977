package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.services.RegisterAirportService;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.AirportType;
import com.marslps.AISafeFMS.model.entities.TimeZone;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;
import com.marslps.AISafeFMS.repository.AirportRepository;
import com.marslps.AISafeFMS.repository.AirportTypeRepository;
import com.marslps.AISafeFMS.repository.TimeZoneRepository;
import java.util.List;

@UseCase
public class RegisterAirportUseCase {
    private final AirportRepository airport_repo;
    private final AirportTypeRepository airport_type_repo;
    private final TimeZoneRepository time_zone_repo;
    private final RegisterAirportService register_airport_service;

    public RegisterAirportUseCase(AirportRepository airport_repo,
                                  AirportTypeRepository airport_type_repo,
                                  TimeZoneRepository time_zone_repo,
                                  RegisterAirportService register_airport_service) {
        this.airport_repo = airport_repo;
        this.airport_type_repo = airport_type_repo;
        this.time_zone_repo = time_zone_repo;
        this.register_airport_service = register_airport_service;
    }

    public Airport execute(String name,
                           LocationIdentifier iata,
                           Coordinates coordinates,
                           AirportLocation airport_location,
                           String airport_type,
                           List<RunwayInfo> runway_info,
                           AirportStatus status,
                           String time_zone,
                           OperationalHours operational_hours,
                           List<ContactInfo> contact_info,
                           List<AirportTerminal> terminals,
                           List<AirportGate> gates,
                           List<AirportService> services,
                           List<String> images) {
        AirportType airport_type_object = airport_type_repo.findByType(airport_type);
        if(airport_type_object == null) {
            airport_type_object = airport_type_repo.save(new AirportType(airport_type));
        }
        TimeZone time_zone_object = time_zone_repo.findByAbbreviation(time_zone);
        if(time_zone_object == null) {
            time_zone_object = time_zone_repo.save(new TimeZone(time_zone, 0 ,0));
        }
        Airport airport = register_airport_service.register(name, iata, coordinates, airport_location, airport_type_object, runway_info, status, time_zone_object, operational_hours, contact_info, terminals, gates, services, images);
        return airport_repo.save(airport);
    }
}

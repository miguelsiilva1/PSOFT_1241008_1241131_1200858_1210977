package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.model.UpdateAirportRequest;
import com.marslps.AISafeFMS.application.services.UpdateAirportService;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.AirportType;
import com.marslps.AISafeFMS.model.entities.TimeZone;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.repository.AirportRepository;
import com.marslps.AISafeFMS.repository.AirportTypeRepository;
import com.marslps.AISafeFMS.repository.TimeZoneRepository;

@UseCase
public class UpdateAirportUseCase {
    private final AirportRepository airport_repository;
    private final AirportTypeRepository airport_type_repository;
    private final TimeZoneRepository time_zone_repository;
    private final UpdateAirportService update_airport_service;

    public UpdateAirportUseCase(AirportRepository airport_repository, AirportTypeRepository airport_type_repository, TimeZoneRepository time_zone_repository, UpdateAirportService update_airport_service) {
        this.airport_repository = airport_repository;
        this.airport_type_repository = airport_type_repository;
        this.time_zone_repository = time_zone_repository;
        this.update_airport_service = update_airport_service;
    }

    public Airport execute(LocationIdentifier iata, UpdateAirportRequest request) {
        Airport airport = airport_repository.findByIata(iata).orElse(null);
        if (airport == null) {
            throw new IllegalArgumentException("The request's data is malformed");
        }

        AirportType type = null;
        if (request.airport_type() != null) {
            type = airport_type_repository.findByType(request.airport_type());
            if (type == null) { type = airport_type_repository.save(new AirportType(request.airport_type())); }
        }

        TimeZone tz = null;
        if (request.time_zone() != null) {
            tz = time_zone_repository.findByAbbreviation(request.time_zone());
            if (tz == null) { tz = time_zone_repository.save(new TimeZone(request.time_zone(), 0, 0)); }
        }

        Airport updated_airport = update_airport_service.update(airport, request.name(), request.coordinates(), request.airport_location(), type, tz, request.operational_hours(), request.runway_info(), request.contact_info(), request.terminals(), request.gates(), request.services(), request.images());
        return airport_repository.save(updated_airport);
    }
}
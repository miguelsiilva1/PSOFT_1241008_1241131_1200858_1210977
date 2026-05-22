package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.services.UpdateAirportService;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.repository.AirportRepository;

@UseCase
public class UpdateAirportStatusUseCase {
    private final AirportRepository airport_repository;
    private final UpdateAirportService update_airport_service;

    public UpdateAirportStatusUseCase(AirportRepository airport_repository, UpdateAirportService update_airport_service) {
        this.airport_repository = airport_repository;
        this.update_airport_service = update_airport_service;
    }

    public Airport execute(LocationIdentifier iata, AirportStatus status) {
        Airport airport = airport_repository.findByIata(iata).orElse(null);
        if (airport == null) {
            throw new IllegalArgumentException("The request's data is malformed");
        }

        Airport updated_airport = update_airport_service.updateStatus(airport, status);
        return airport_repository.save(updated_airport);
    }
}
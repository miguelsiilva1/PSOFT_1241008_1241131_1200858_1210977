package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.services.CertifyAircraftService;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;
import com.marslps.AISafeFMS.repository.AirportRepository;

import java.util.List;

@UseCase
public class CertifyAircraftUseCase {
    private final AirportRepository airport_repository;
    private final AircraftModelRepository aircraft_model_repo;
    private final CertifyAircraftService certify_aircraft_service;

    public CertifyAircraftUseCase(AirportRepository airport_repository,
                                  AircraftModelRepository aircraft_model_repo,
                                  CertifyAircraftService certify_aircraft_service) {
        this.airport_repository = airport_repository;
        this.aircraft_model_repo = aircraft_model_repo;
        this.certify_aircraft_service = certify_aircraft_service;
    }

    public Airport execute(LocationIdentifier iata, String name) {
        Airport airport = airport_repository.findByIata(iata).orElse(null);
        if (airport == null) {
            throw new IllegalArgumentException("The request's data is malformed");
        }

        List<AircraftModel> models = aircraft_model_repo.findByName(name);
        if (models == null || models.isEmpty()) {
            throw new IllegalArgumentException("The request's data is malformed");
        }
        
        certify_aircraft_service.certify(airport, models.get(0));
        return airport_repository.save(airport);
    }
}
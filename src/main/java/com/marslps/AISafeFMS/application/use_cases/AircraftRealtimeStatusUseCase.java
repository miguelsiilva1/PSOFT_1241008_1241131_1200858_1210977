package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.repository.AircraftRepository;

@UseCase
public class AircraftRealtimeStatusUseCase {
    private final AircraftRepository aircraft_repo;

    public AircraftRealtimeStatusUseCase(AircraftRepository aircraft_repo) {
        this.aircraft_repo = aircraft_repo;
    }

    public Aircraft execute(String registration_number) {
        return aircraft_repo.findByRegistrationNumber(registration_number);
    }
}

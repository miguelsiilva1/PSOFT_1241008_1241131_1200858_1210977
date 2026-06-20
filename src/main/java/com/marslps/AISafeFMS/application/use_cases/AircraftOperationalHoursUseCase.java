package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.repository.AircraftRepository;

import java.util.List;

@UseCase
public class AircraftOperationalHoursUseCase {
    private final AircraftRepository aircraft_repo;

    public AircraftOperationalHoursUseCase(AircraftRepository aircraft_repo) {
        this.aircraft_repo = aircraft_repo;
    }

    public Iterable<Aircraft> execute() {
        return aircraft_repo.findAll();
    }
}

package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.repository.AircraftRepository;

@UseCase
public class FindAircraftByRegistrationNumberUseCase {
    private final AircraftRepository aircraft_repo;

    public FindAircraftByRegistrationNumberUseCase(AircraftRepository aircraft_repo) {
        this.aircraft_repo = aircraft_repo;
    }

    public Aircraft execute(String registration_number) {
        Aircraft aircraft_object = aircraft_repo.findByRegistrationNumber(registration_number);
        if (aircraft_object == null) {
            throw new IllegalArgumentException("The Aircraft does not exist in the system");
        }
        return aircraft_object;
    }
}

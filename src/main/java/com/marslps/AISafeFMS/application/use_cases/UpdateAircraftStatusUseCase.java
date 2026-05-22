package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.services.UpdateAircraftStatusService;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.repository.AircraftRepository;

@UseCase
public class UpdateAircraftStatusUseCase {
    private final AircraftRepository aircraft_repo;
    private final UpdateAircraftStatusService update_aircraft_status_service;

    public UpdateAircraftStatusUseCase(AircraftRepository aircraft_repo, UpdateAircraftStatusService updateAircraftStatusService) {
        this.aircraft_repo = aircraft_repo;
        update_aircraft_status_service = updateAircraftStatusService;
    }

    public Aircraft execute(String registration_number, AircraftStatus status) {
        Aircraft aircraft_object = aircraft_repo.findByRegistrationNumber(registration_number);
        if (aircraft_object == null) {
            throw new IllegalArgumentException("The Aircraft does not exist in the system");
        }
        return update_aircraft_status_service.update(aircraft_object.obtainRegistrationNumber(), status);
    }
}

package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateAircraftStatusServiceImpl implements UpdateAircraftStatusService {
    private final AircraftRepository aircraft_repo;

    public UpdateAircraftStatusServiceImpl(AircraftRepository aircraft_repo) {
        this.aircraft_repo = aircraft_repo;
    }

    @Override
    public Aircraft update(String registration_number, AircraftStatus status) {
        return aircraft_repo.updateAircraftStatus(registration_number, status);
    }
}

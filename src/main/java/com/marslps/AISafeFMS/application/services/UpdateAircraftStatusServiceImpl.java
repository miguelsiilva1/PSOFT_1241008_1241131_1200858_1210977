package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UpdateAircraftStatusServiceImpl implements UpdateAircraftStatusService {
    private final AircraftRepository aircraft_repo;

    public UpdateAircraftStatusServiceImpl(AircraftRepository aircraft_repo) {
        this.aircraft_repo = aircraft_repo;
    }

    @Override
    @Transactional
    public Aircraft update(String registration_number, AircraftStatus status) {
        int rows_updated = aircraft_repo.updateAircraftStatus(registration_number, status);
        if (rows_updated == 0) {
            throw new IllegalArgumentException("The Aircraft does not exist in the system");
        }
        return aircraft_repo.findByRegistrationNumber(registration_number);
    }
}

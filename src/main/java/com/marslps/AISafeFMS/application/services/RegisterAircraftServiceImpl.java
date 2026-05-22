package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.SeatingConfiguration;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

@Service
public class RegisterAircraftServiceImpl implements RegisterAircraftService {
    private final AircraftRepository aircraft_repo;

    public RegisterAircraftServiceImpl(AircraftRepository aircraft_repo) {
        this.aircraft_repo = aircraft_repo;
    }

    @Override
    public Aircraft register(AircraftRegistration aircraft_registration,
                             AircraftModel aircraft_model,
                             Date manufacturing_date,
                             Date last_maintenance,
                             int max_flight_hours_until_maintenance,
                             int max_days_until_maintenance,
                             SeatingConfiguration seating_configuration,
                             AircraftStatus aircraft_status) {
        if(!aircraft_repo.alreadyExists(aircraft_registration.registration_number())) {
            return new Aircraft(aircraft_registration,
                                aircraft_model,
                                seating_configuration,
                                manufacturing_date,
                                last_maintenance,
                                "",
                                max_flight_hours_until_maintenance,
                                max_days_until_maintenance,
                                aircraft_status);
        }
        throw new IllegalArgumentException("This Aircraft already exists.");
    }
}

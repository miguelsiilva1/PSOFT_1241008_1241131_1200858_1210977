package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RegisterAircraftModelServiceImpl implements RegisterAircraftModelService {
    private final AircraftModelRepository aircraft_model_repo;

    public RegisterAircraftModelServiceImpl(AircraftModelRepository aircraft_model_repo) {
        this.aircraft_model_repo = aircraft_model_repo;
    }

    @Override
    public AircraftModel register(String name, int seating_capacity, double max_range, double fuel_capacity, double cruising_speed, AircraftManufacturer manufacturer) {
        if(!aircraft_model_repo.alreadyExists(name, seating_capacity, max_range, fuel_capacity, cruising_speed)) {
            return new AircraftModel(name, seating_capacity, max_range, fuel_capacity, cruising_speed, manufacturer, new HashSet<>());
        }
        throw new IllegalArgumentException("This Aircraft Model already exists.");
    }
}
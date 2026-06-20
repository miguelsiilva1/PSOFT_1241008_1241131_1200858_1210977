package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UpdateAircraftModelServiceImpl implements UpdateAircraftModelService {
    private final AircraftModelRepository aircraft_model_repo;

    public UpdateAircraftModelServiceImpl(AircraftModelRepository aircraft_model_repo) {
        this.aircraft_model_repo = aircraft_model_repo;
    }

    @Override
    @Transactional
    public AircraftModel update(@NotBlank String name,
                                @Positive int seating_capacity,
                                @Positive double max_range,
                                @Positive double fuel_capacity,
                                @Positive double cruising_speed,
                                AircraftManufacturer manufacturer_object,
                                @Positive int new_seating_capacity,
                                @Positive double new_max_range,
                                @Positive double new_fuel_capacity,
                                @Positive double new_cruising_speed) {
        AircraftModel old_aircraft_model = aircraft_model_repo.findAircraftModel(name, seating_capacity, max_range, fuel_capacity, cruising_speed, manufacturer_object.obtainName());
        if(old_aircraft_model != null) {
            int rows_updated = aircraft_model_repo.update(old_aircraft_model, new_seating_capacity, new_max_range, new_fuel_capacity, new_cruising_speed);
            if(rows_updated == 0) {throw new IllegalArgumentException("The Aircraft Model does not exist.");}
            return aircraft_model_repo.findAircraftModel(name, new_seating_capacity, new_max_range, new_fuel_capacity, new_cruising_speed, manufacturer_object.obtainName());
        }
        else {
            throw new IllegalArgumentException("The Aircraft Model does not exist.");
        }
    }
}

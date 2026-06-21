package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface UpdateAircraftModelService {
    @Transactional
    AircraftModel update(@NotBlank String name,
                         @Positive int seating_capacity,
                         @Positive double max_range,
                         @Positive double fuel_capacity,
                         @Positive double cruising_speed,
                         AircraftManufacturer manufacturer_name,
                         @Positive int new_seating_capacity,
                         @Positive double new_max_range,
                         @Positive double new_fuel_capacity,
                         @Positive double new_cruising_speed);
}

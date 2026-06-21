package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;

import com.marslps.AISafeFMS.application.services.UpdateAircraftModelService;
import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.repository.AircraftManufacturerRepository;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@UseCase
public class UpdateAircraftModelUseCase {
    private final AircraftManufacturerRepository aircraft_manu_repo;
    private final UpdateAircraftModelService update_am_service;

    public UpdateAircraftModelUseCase(AircraftManufacturerRepository aircraft_manu_repo,
                                        UpdateAircraftModelService update_am_service) {
        this.aircraft_manu_repo = aircraft_manu_repo;
        this.update_am_service = update_am_service;
    }

    public AircraftModel execute(@NotBlank String name,
                                 @Positive int seating_capacity,
                                 @Positive double max_range,
                                 @Positive double fuel_capacity,
                                 @Positive double cruising_speed,
                                 @NotBlank String manufacturer_name,
                                 @Positive int new_seating_capacity,
                                 @Positive double new_max_range,
                                 @Positive double new_fuel_capacity,
                                 @Positive double new_cruising_speed) {
        AircraftManufacturer manufacturer_object = aircraft_manu_repo.findByName(manufacturer_name);
        if (manufacturer_object == null) {
            throw new IllegalArgumentException("The manufacturer does not exist in the system");
        }
        return update_am_service.update(name, seating_capacity, max_range, fuel_capacity, cruising_speed, manufacturer_object, new_seating_capacity, new_max_range, new_fuel_capacity, new_cruising_speed);
    }
}

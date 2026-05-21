package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.services.RegisterAircraftModelService;
import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.repository.AircraftManufacturerRepository;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;

@UseCase
public class RegisterAircraftModelUseCase {
    private final AircraftModelRepository aircraft_model_repo;
    private final AircraftManufacturerRepository aircraft_manu_repo;
    private final RegisterAircraftModelService register_am_service;

    public RegisterAircraftModelUseCase(AircraftModelRepository aircraft_model_repo,
                                        AircraftManufacturerRepository aircraft_manu_repo,
                                        RegisterAircraftModelService register_am_service) {
        this.aircraft_model_repo = aircraft_model_repo;
        this.aircraft_manu_repo = aircraft_manu_repo;
        this.register_am_service = register_am_service;
    }

    public AircraftModel execute(String name,
                        int seating_capacity,
                        double max_range,
                        double fuel_capacity,
                        double cruising_speed,
                        String manufacturer_name) {
        AircraftManufacturer manufacturer_object = aircraft_manu_repo.findByName(manufacturer_name);
        if (manufacturer_object == null) {
            throw new IllegalArgumentException("The manufacturer does not exist in the system");
        }
        AircraftModel aircraft_model = register_am_service.register(name, seating_capacity, max_range, fuel_capacity, cruising_speed, manufacturer_object);
        return aircraft_model_repo.save(aircraft_model);
    }
}

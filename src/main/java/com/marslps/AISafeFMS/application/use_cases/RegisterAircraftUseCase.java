package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.services.RegisterAircraftModelService;
import com.marslps.AISafeFMS.application.services.RegisterAircraftService;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.SeatingConfiguration;
import com.marslps.AISafeFMS.repository.AircraftManufacturerRepository;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

@UseCase
public class RegisterAircraftUseCase {
    private final AircraftRepository aircraft_repo;
    private final AircraftModelRepository aircraft_model_repo;
    private final RegisterAircraftService register_aircraft_service;

    public RegisterAircraftUseCase(AircraftRepository aircraft_repo,
                                        AircraftModelRepository aircraft_model_repo,
                                        RegisterAircraftService register_aircraft_service) {
        this.aircraft_repo = aircraft_repo;
        this.aircraft_model_repo = aircraft_model_repo;
        this.register_aircraft_service = register_aircraft_service;
    }

    @Transactional
    public Aircraft execute(String registration_number,
                            String model_name,
                            Date manufacturing_date,
                            double operational_hours,
                            double total_flight_hours,
                            Date last_maintenance,
                            int max_flight_hours_until_maintenance,
                            int max_days_until_maintenance,
                            int economy_seats,
                            int premium_economy_seats,
                            int business_seats,
                            int first_seats,
                            String status,
                            String image) {
        AircraftModel aircraft_model_object = aircraft_model_repo.findByNameAndSeatingCapacity(model_name, economy_seats + premium_economy_seats + business_seats + first_seats);
        if (aircraft_model_object == null) {
            throw new IllegalArgumentException("The aircraft model does not exist in the system");
        }
        AircraftRegistration aircraft_registration = new AircraftRegistration(registration_number);
        AircraftStatus aircraft_status = AircraftStatus.valueOf(status);
        SeatingConfiguration seating_configuration = new SeatingConfiguration(economy_seats, premium_economy_seats, business_seats, first_seats);
        Aircraft aircraft = register_aircraft_service.register(aircraft_registration, aircraft_model_object, manufacturing_date, operational_hours, total_flight_hours, last_maintenance, max_flight_hours_until_maintenance, max_days_until_maintenance, seating_configuration, aircraft_status, image);
        return aircraft_repo.save(aircraft);
    }
}

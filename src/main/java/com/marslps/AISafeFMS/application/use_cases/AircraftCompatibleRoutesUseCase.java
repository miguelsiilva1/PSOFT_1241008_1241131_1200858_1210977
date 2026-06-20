package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import com.marslps.AISafeFMS.repository.RouteRepository;

import java.util.List;

@UseCase
public class AircraftCompatibleRoutesUseCase {
    private final AircraftRepository aircraft_repo;
    private final RouteRepository route_repo;

    public AircraftCompatibleRoutesUseCase(AircraftRepository aircraft_repo,
                                           RouteRepository route_repo) {
        this.aircraft_repo = aircraft_repo;
        this.route_repo = route_repo;
    }

    public List<Route> execute(String registration_number) {
        Aircraft aircraft = aircraft_repo.findByRegistrationNumber(registration_number);
        if(aircraft != null) {
            return route_repo.findValidRoutesForAircraft(aircraft.obtainRange(), aircraft.obtainCapacity());
        }
        else {
            throw new IllegalArgumentException("The aircraft does not exist in the system.");
        }
    }
}

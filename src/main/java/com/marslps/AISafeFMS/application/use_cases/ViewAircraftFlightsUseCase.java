package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.model.AircraftFlightsResponse;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.Flight;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import com.marslps.AISafeFMS.repository.FlightRepository;

import java.util.List;

@UseCase
public class ViewAircraftFlightsUseCase {

    private final AircraftRepository aircraftRepository;
    private final FlightRepository flightRepository;

    public ViewAircraftFlightsUseCase(AircraftRepository aircraftRepository,
                                      FlightRepository flightRepository) {
        this.aircraftRepository = aircraftRepository;
        this.flightRepository = flightRepository;
    }

    public List<AircraftFlightsResponse> execute(String aircraftRegistration) {
        Aircraft aircraft = aircraftRepository.findByRegistrationNumber(aircraftRegistration);

        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft not found.");
        }

        List<Flight> flights = flightRepository.findByAircraftOrderByScheduledDepartureAsc(aircraft);

        return flights.stream()
                .map(AircraftFlightsResponse::from)
                .toList();
    }
}
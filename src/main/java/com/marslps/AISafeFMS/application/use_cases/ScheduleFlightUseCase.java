package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.model.ScheduleFlightRequest;
import com.marslps.AISafeFMS.application.model.ScheduleFlightResponse;
import com.marslps.AISafeFMS.application.services.ScheduleFlightServiceImpl;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.Flight;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.vo.RouteID;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import com.marslps.AISafeFMS.repository.FlightRepository;
import com.marslps.AISafeFMS.repository.RouteRepository;

@UseCase
public class ScheduleFlightUseCase {

    private final RouteRepository routeRepository;
    private final AircraftRepository aircraftRepository;
    private final FlightRepository flightRepository;
    private final ScheduleFlightServiceImpl scheduleFlightService;

    public ScheduleFlightUseCase(RouteRepository routeRepository,
                                 AircraftRepository aircraftRepository,
                                 FlightRepository flightRepository,
                                 ScheduleFlightServiceImpl scheduleFlightService) {
        this.routeRepository = routeRepository;
        this.aircraftRepository = aircraftRepository;
        this.flightRepository = flightRepository;
        this.scheduleFlightService = scheduleFlightService;
    }

    public ScheduleFlightResponse execute(ScheduleFlightRequest request) {
        Route route = routeRepository.findByRouteId(new RouteID(request.route_id()))
                .orElseThrow(() -> new IllegalArgumentException("Route not found."));

        Aircraft aircraft = aircraftRepository.findByRegistrationNumber(request.aircraft_registration());

        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft not found.");
        }

        Flight flight = scheduleFlightService.schedule(route, aircraft, request.scheduled_departure());
        flightRepository.save(flight);

        return new ScheduleFlightResponse(
                scheduleFlightService.generateFlightIDValue(route, aircraft, request.scheduled_departure()),
                route.obtainRouteID().id(),
                aircraft.obtainRegistrationNumber(),
                request.scheduled_departure(),
                "SCHEDULED"
        );
    }
}
package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.repository.FlightRepository;
import com.marslps.AISafeFMS.repository.RouteRepository;

import java.util.Comparator;
import java.util.List;

@UseCase
public class ListActiveRoutesUseCase {

    private final RouteRepository routeRepository;
    private final FlightRepository flightRepository;

    public ListActiveRoutesUseCase(RouteRepository routeRepository,
                                   FlightRepository flightRepository) {
        this.routeRepository = routeRepository;
        this.flightRepository = flightRepository;
    }

    public List<Route> execute(String sortBy) {
        if (sortBy == null || sortBy.isBlank() || sortBy.equalsIgnoreCase("distance")) {
            return routeRepository.findActiveRoutesOrderByDistance();
        }

        if (sortBy.equalsIgnoreCase("popularity")) {
            return routeRepository.findActiveRoutes()
                    .stream()
                    .sorted(Comparator.comparingLong(flightRepository::countByRoute).reversed())
                    .toList();
        }

        throw new IllegalArgumentException("Invalid sort option. Use 'distance' or 'popularity'.");
    }
}
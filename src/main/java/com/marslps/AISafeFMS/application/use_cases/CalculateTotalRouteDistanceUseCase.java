package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.repository.RouteRepository;

@UseCase
public class CalculateTotalRouteDistanceUseCase {

    private final RouteRepository routeRepository;

    public CalculateTotalRouteDistanceUseCase(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public double execute() {
        return routeRepository.findActiveRoutes()
                .stream()
                .mapToDouble(Route::getFlight_distance)
                .sum();
    }
}
package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.services.RouteService;
import com.marslps.AISafeFMS.model.entities.Route;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewRoutesFromAirportUseCase {

    private final RouteService routeService;

    public ViewRoutesFromAirportUseCase(RouteService routeService) {
        this.routeService = routeService;
    }

    public List<Route> execute(String iataCode) {
        return routeService.getRoutesFromAirport(iataCode);
    }
}
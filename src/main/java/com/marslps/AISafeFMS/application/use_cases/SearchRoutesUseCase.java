package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.services.RouteService;
import com.marslps.AISafeFMS.model.entities.Route;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchRoutesUseCase {

    private final RouteService routeService;

    public SearchRoutesUseCase(RouteService routeService) {
        this.routeService = routeService;
    }

    public List<Route> execute(String origin, String destination) {
        return routeService.searchRoutes(origin, destination);
    }
}
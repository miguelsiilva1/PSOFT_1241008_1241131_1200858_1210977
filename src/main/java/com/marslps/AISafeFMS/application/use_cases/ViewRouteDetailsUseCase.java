package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.services.RouteService;
import com.marslps.AISafeFMS.model.entities.Route;
import org.springframework.stereotype.Component;

@Component
public class ViewRouteDetailsUseCase {

    private final RouteService routeService;

    public ViewRouteDetailsUseCase(RouteService routeService) {
        this.routeService = routeService;
    }

    public Route execute(String routeId) {
        return routeService.getRouteById(routeId);
    }
}
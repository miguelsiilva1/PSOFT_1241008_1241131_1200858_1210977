package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.model.CreateRouteRequest;
import com.marslps.AISafeFMS.application.services.RouteService;
import com.marslps.AISafeFMS.model.entities.Route;
import org.springframework.stereotype.Component;

@Component
public class CreateRouteUseCase {

    private final RouteService routeService;

    public CreateRouteUseCase(RouteService routeService) {
        this.routeService = routeService;
    }

    public Route execute(CreateRouteRequest request) {
        return routeService.createRoute(request);
    }
}
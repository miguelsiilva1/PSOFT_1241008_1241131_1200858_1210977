package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.model.UpdateRouteRequest;
import com.marslps.AISafeFMS.application.services.RouteService;
import com.marslps.AISafeFMS.model.entities.Route;
import org.springframework.stereotype.Component;

@Component
public class UpdateRouteUseCase {

    private final RouteService routeService;

    public UpdateRouteUseCase(RouteService routeService) {
        this.routeService = routeService;
    }

    public Route execute(String routeId, UpdateRouteRequest request) {
        return routeService.updateRoute(routeId, request);
    }
}
package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.services.RouteService;
import com.marslps.AISafeFMS.model.entities.RouteHistory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewRouteHistoryUseCase {

    private final RouteService routeService;

    public ViewRouteHistoryUseCase(RouteService routeService) {
        this.routeService = routeService;
    }

    public List<RouteHistory> execute(String routeId) {
        return routeService.getRouteHistory(routeId);
    }
}

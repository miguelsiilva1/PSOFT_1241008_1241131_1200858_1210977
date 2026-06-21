package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.dto.AirportStatisticsDTO;
import com.marslps.AISafeFMS.application.services.GenerateStatisticsService;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.repository.RouteRepository;

import java.util.List;

@UseCase
public class GenerateStatisticsUseCase {
    private final RouteRepository route_repository;
    private final GenerateStatisticsService statistics_service;

    public GenerateStatisticsUseCase(RouteRepository route_repository, GenerateStatisticsService statistics_service) {
        this.route_repository = route_repository;
        this.statistics_service = statistics_service;
    }

    public List<AirportStatisticsDTO> execute() {
        List<Route> all_routes = route_repository.findAll();
        return statistics_service.generateStatistics(all_routes);
    }
}
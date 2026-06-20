package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.repository.AirportRepository;
import com.marslps.AISafeFMS.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;

@UseCase
public class ViewAirportRoutesUseCase {
    private final AirportRepository airport_repository;
    private final RouteRepository route_repository;

    public ViewAirportRoutesUseCase(AirportRepository airport_repository, RouteRepository route_repository) {
        this.airport_repository = airport_repository;
        this.route_repository = route_repository;
    }

    public List<Route> execute(LocationIdentifier iata) {
        Airport airport = airport_repository.findByIata(iata).orElse(null);
        if (airport == null) {
            throw new IllegalArgumentException("Airport not found.");
        }

        List<Route> routes = new ArrayList<>(route_repository.findByDeparture_Iata(iata));
        routes.addAll(route_repository.findByDestination_Iata(iata));
        return routes;
    }
}
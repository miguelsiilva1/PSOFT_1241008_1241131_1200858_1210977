package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.model.AlternativeRouteResponse;
import com.marslps.AISafeFMS.application.model.RouteResponse;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.repository.AirportRepository;
import com.marslps.AISafeFMS.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;

@UseCase
public class SearchAlternativeRoutesUseCase {

    private final RouteRepository routeRepository;
    private final AirportRepository airportRepository;

    public SearchAlternativeRoutesUseCase(RouteRepository routeRepository,
                                          AirportRepository airportRepository) {
        this.routeRepository = routeRepository;
        this.airportRepository = airportRepository;
    }

    public List<AlternativeRouteResponse> execute(String origin, String destination) {
        if (origin == null || origin.isBlank()) {
            throw new IllegalArgumentException("Origin airport is required.");
        }

        if (destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Destination airport is required.");
        }

        LocationIdentifier originIata = new LocationIdentifier(origin);
        LocationIdentifier destinationIata = new LocationIdentifier(destination);

        if (airportRepository.findByIata(originIata).isEmpty()) {
            throw new IllegalArgumentException("Origin airport not found.");
        }

        if (airportRepository.findByIata(destinationIata).isEmpty()) {
            throw new IllegalArgumentException("Destination airport not found.");
        }

        List<Route> routesFromOrigin = routeRepository.findByDeparture_Iata(originIata)
                .stream()
                .filter(Route::isActive)
                .toList();

        List<AlternativeRouteResponse> alternatives = new ArrayList<>();

        for (Route firstLeg : routesFromOrigin) {
            LocationIdentifier connectionIata = firstLeg.obtainDestination().obtainIata();

            if (connectionIata.equals(destinationIata)) {
                continue;
            }

            List<Route> routesToDestination = routeRepository
                    .findByDeparture_IataAndDestination_Iata(connectionIata, destinationIata)
                    .stream()
                    .filter(Route::isActive)
                    .toList();

            for (Route secondLeg : routesToDestination) {
                double totalDistance = firstLeg.getFlight_distance() + secondLeg.getFlight_distance();

                alternatives.add(new AlternativeRouteResponse(
                        RouteResponse.from(firstLeg),
                        RouteResponse.from(secondLeg),
                        totalDistance
                ));
            }
        }

        return alternatives;
    }
}
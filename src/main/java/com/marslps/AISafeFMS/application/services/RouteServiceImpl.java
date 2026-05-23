package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.application.model.CreateRouteRequest;
import com.marslps.AISafeFMS.application.model.UpdateRouteRequest;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.entities.RouteHistory;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.model.vo.RouteID;
import com.marslps.AISafeFMS.repository.AirportRepository;
import com.marslps.AISafeFMS.repository.RouteHistoryRepository;
import com.marslps.AISafeFMS.repository.RouteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final RouteHistoryRepository routeHistoryRepository;
    private final AirportRepository airportRepository;

    public RouteServiceImpl(RouteRepository routeRepository,
                            RouteHistoryRepository routeHistoryRepository,
                            AirportRepository airportRepository) {
        this.routeRepository = routeRepository;
        this.routeHistoryRepository = routeHistoryRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    @Transactional
    public Route createRoute(CreateRouteRequest request) {
        LocationIdentifier departureIata = new LocationIdentifier(request.departure_iata());
        LocationIdentifier destinationIata = new LocationIdentifier(request.destination_iata());

        Airport departure = airportRepository.findByIata(departureIata)
                .orElseThrow(() -> new IllegalArgumentException("Departure airport not found."));

        Airport destination = airportRepository.findByIata(destinationIata)
                .orElseThrow(() -> new IllegalArgumentException("Destination airport not found."));

        if (departure.getStatus() != AirportStatus.OPERATIONAL || destination.getStatus() != AirportStatus.OPERATIONAL) {
            throw new IllegalArgumentException("Both airports must be operational.");
        }

        String routeIdValue = departure.obtainIata().iata() + "-" + destination.obtainIata().iata();

        if (routeRepository.existsById_Id(routeIdValue)) {
            throw new IllegalArgumentException("Route already exists.");
        }

        Route route = new Route(
                new RouteID(routeIdValue),
                departure,
                destination,
                request.estimated_flight_time(),
                request.flight_distance(),
                request.min_aircraft_range(),
                request.min_aircraft_capacity(),
                true
        );

        Route savedRoute = routeRepository.save(route);
        routeHistoryRepository.save(new RouteHistory(savedRoute, "Route created."));

        return savedRoute;
    }

    @Override
    public List<RouteHistory> getRouteHistory(String routeId) {
        if (!routeRepository.existsById_Id(routeId)) {
            throw new IllegalArgumentException("Route not found.");
        }

        return routeHistoryRepository.findByRoute_Id_Id(routeId);
    }

    @Override
    @Transactional
    public Route updateRoute(String routeId, UpdateRouteRequest request) {
        Route route = routeRepository.findById_Id(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found."));

        route.updateRoute(
                request.estimated_flight_time(),
                request.flight_distance(),
                request.min_aircraft_range(),
                request.min_aircraft_capacity()
        );

        Route updatedRoute = routeRepository.save(route);
        routeHistoryRepository.save(new RouteHistory(updatedRoute, "Route updated."));

        return updatedRoute;
    }

    @Override
    @Transactional
    public Route deactivateRoute(String routeId) {
        Route route = routeRepository.findById_Id(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found."));

        route.deactivate();

        Route deactivatedRoute = routeRepository.save(route);
        routeHistoryRepository.save(new RouteHistory(deactivatedRoute, "Route deactivated."));

        return deactivatedRoute;
    }

    @Override
    public Route getRouteById(String routeId) {
        return routeRepository.findById_Id(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found."));
    }

    @Override
    public List<Route> getRoutesFromAirport(String iataCode) {
        LocationIdentifier iata = new LocationIdentifier(iataCode);

        if (airportRepository.findByIata(iata).isEmpty()) {
            throw new IllegalArgumentException("Airport not found.");
        }

        return routeRepository.findByDeparture_Iata(iata);
    }

    @Override
    public List<Route> searchRoutes(String origin, String destination) {
        boolean hasOrigin = origin != null && !origin.isBlank();
        boolean hasDestination = destination != null && !destination.isBlank();

        if (!hasOrigin && !hasDestination) {
            throw new IllegalArgumentException("Origin or destination is required.");
        }

        if (hasOrigin && hasDestination) {
            LocationIdentifier originIata = new LocationIdentifier(origin);
            LocationIdentifier destinationIata = new LocationIdentifier(destination);

            if (airportRepository.findByIata(originIata).isEmpty()) {
                throw new IllegalArgumentException("Origin airport not found.");
            }

            if (airportRepository.findByIata(destinationIata).isEmpty()) {
                throw new IllegalArgumentException("Destination airport not found.");
            }

            return routeRepository.findByDeparture_IataAndDestination_Iata(originIata, destinationIata);
        }

        if (hasOrigin) {
            LocationIdentifier originIata = new LocationIdentifier(origin);

            if (airportRepository.findByIata(originIata).isEmpty()) {
                throw new IllegalArgumentException("Origin airport not found.");
            }

            return routeRepository.findByDeparture_Iata(originIata);
        }

        LocationIdentifier destinationIata = new LocationIdentifier(destination);

        if (airportRepository.findByIata(destinationIata).isEmpty()) {
            throw new IllegalArgumentException("Destination airport not found.");
        }

        return routeRepository.findByDestination_Iata(destinationIata);
    }
}
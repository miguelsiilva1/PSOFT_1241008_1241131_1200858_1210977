package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.application.model.CreateRouteRequest;
import com.marslps.AISafeFMS.application.model.UpdateRouteRequest;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.entities.RouteHistory;

import java.util.List;

public interface RouteService {

    Route createRoute(CreateRouteRequest request);

    List<RouteHistory> getRouteHistory(String routeId);

    Route updateRoute(String routeId, UpdateRouteRequest request);

    Route deactivateRoute(String routeId);

    Route getRouteById(String routeId);

    List<Route> getRoutesFromAirport(String iataCode);

    List<Route> searchRoutes(String origin, String destination);
}
package com.marslps.AISafeFMS.application.controllers;

import com.marslps.AISafeFMS.application.model.CreateRouteRequest;
import com.marslps.AISafeFMS.application.model.RouteHistoryResponse;
import com.marslps.AISafeFMS.application.model.RouteResponse;
import com.marslps.AISafeFMS.application.model.UpdateRouteRequest;
import com.marslps.AISafeFMS.application.use_cases.*;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.entities.RouteHistory;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final CreateRouteUseCase createRouteUseCase;
    private final ViewRouteHistoryUseCase viewRouteHistoryUseCase;
    private final UpdateRouteUseCase updateRouteUseCase;
    private final DeactivateRouteUseCase deactivateRouteUseCase;
    private final ViewRouteDetailsUseCase viewRouteDetailsUseCase;
    private final ViewRoutesFromAirportUseCase viewRoutesFromAirportUseCase;
    private final SearchRoutesUseCase searchRoutesUseCase;

    public RouteController(CreateRouteUseCase createRouteUseCase,
                           ViewRouteHistoryUseCase viewRouteHistoryUseCase,
                           UpdateRouteUseCase updateRouteUseCase,
                           DeactivateRouteUseCase deactivateRouteUseCase,
                           ViewRouteDetailsUseCase viewRouteDetailsUseCase,
                           ViewRoutesFromAirportUseCase viewRoutesFromAirportUseCase,
                           SearchRoutesUseCase searchRoutesUseCase) {
        this.createRouteUseCase = createRouteUseCase;
        this.viewRouteHistoryUseCase = viewRouteHistoryUseCase;
        this.updateRouteUseCase = updateRouteUseCase;
        this.deactivateRouteUseCase = deactivateRouteUseCase;
        this.viewRouteDetailsUseCase = viewRouteDetailsUseCase;
        this.viewRoutesFromAirportUseCase = viewRoutesFromAirportUseCase;
        this.searchRoutesUseCase = searchRoutesUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ATCC')")
    public ResponseEntity<RouteResponse> createRoute(@Valid @RequestBody CreateRouteRequest request) {
        Route route = createRouteUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RouteResponse.from(route));
    }

    @GetMapping("/{routeId}")
    @PreAuthorize("hasAnyRole('ATCC', 'BACKOFFICE_OPERATOR')")
    public ResponseEntity<RouteResponse> getRouteById(@PathVariable String routeId) {
        Route route = viewRouteDetailsUseCase.execute(routeId);
        return ResponseEntity.ok(RouteResponse.from(route));
    }

    @GetMapping("/{routeId}/history")
    @PreAuthorize("hasAnyRole('ATCC', 'BACKOFFICE_OPERATOR')")
    public ResponseEntity<List<RouteHistoryResponse>> getRouteHistory(@PathVariable String routeId) {
        List<RouteHistory> history = viewRouteHistoryUseCase.execute(routeId);

        List<RouteHistoryResponse> response = history.stream()
                .map(RouteHistoryResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{routeId}")
    @PreAuthorize("hasAnyRole('ATCC', 'BACKOFFICE_OPERATOR')")
    public ResponseEntity<RouteResponse> updateRoute(@PathVariable String routeId,
                                                     @Valid @RequestBody UpdateRouteRequest request) {
        Route route = updateRouteUseCase.execute(routeId, request);
        return ResponseEntity.ok(RouteResponse.from(route));
    }

    @PatchMapping("/{routeId}/deactivate")
    @PreAuthorize("hasAnyRole('ATCC', 'BACKOFFICE_OPERATOR')")
    public ResponseEntity<RouteResponse> deactivateRoute(@PathVariable String routeId) {
        Route route = deactivateRouteUseCase.execute(routeId);
        return ResponseEntity.ok(RouteResponse.from(route));
    }

    @GetMapping("/from/{iataCode}")
    @PreAuthorize("hasAnyRole('ATCC', 'BACKOFFICE_OPERATOR')")
    public ResponseEntity<List<RouteResponse>> getRoutesFromAirport(@PathVariable String iataCode) {
        List<Route> routes = viewRoutesFromAirportUseCase.execute(iataCode);

        List<RouteResponse> response = routes.stream()
                .map(RouteResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ATCC')")
    public ResponseEntity<List<RouteResponse>> searchRoutes(@RequestParam(required = false) String origin,
                                                            @RequestParam(required = false) String destination) {
        List<Route> routes = searchRoutesUseCase.execute(origin, destination);

        List<RouteResponse> response = routes.stream()
                .map(RouteResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
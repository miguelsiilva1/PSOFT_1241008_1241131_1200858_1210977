package com.marslps.AISafeFMS.application.controllers;

import com.marslps.AISafeFMS.application.model.AircraftFlightsResponse;
import com.marslps.AISafeFMS.application.model.ScheduleFlightRequest;
import com.marslps.AISafeFMS.application.model.ScheduleFlightResponse;
import com.marslps.AISafeFMS.application.use_cases.ScheduleFlightUseCase;
import com.marslps.AISafeFMS.application.use_cases.ViewAircraftFlightsUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final ScheduleFlightUseCase scheduleFlightUseCase;
    private final ViewAircraftFlightsUseCase viewAircraftFlightsUseCase;

    public FlightController(ScheduleFlightUseCase scheduleFlightUseCase,
                            ViewAircraftFlightsUseCase viewAircraftFlightsUseCase) {
        this.scheduleFlightUseCase = scheduleFlightUseCase;
        this.viewAircraftFlightsUseCase = viewAircraftFlightsUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> scheduleFlight(@Valid @RequestBody ScheduleFlightRequest request) {
        try {
            ScheduleFlightResponse response = scheduleFlightUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/aircraft/{registration}")
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> viewAircraftFlights(@PathVariable String registration) {
        try {
            List<AircraftFlightsResponse> response = viewAircraftFlightsUseCase.execute(registration);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
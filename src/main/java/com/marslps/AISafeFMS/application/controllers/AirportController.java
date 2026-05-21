package com.marslps.AISafeFMS.application.controllers;

import com.marslps.AISafeFMS.application.use_cases.RegisterAirportUseCase;
import com.marslps.AISafeFMS.application.model.RegisterAirportRequest;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.vo.*;

import com.marslps.AISafeFMS.repository.AirportRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final RegisterAirportUseCase register_airport;
    private final AirportRepository airport_repository;

    public AirportController(RegisterAirportUseCase register_airport, AirportRepository airport_repository) {
        this.register_airport = register_airport;
        this.airport_repository = airport_repository;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('BACKOFFICE_OP')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerAirport(@Valid @RequestBody RegisterAirportRequest request) {
        try {
            Airport airport = register_airport.execute(
                    request.name(),
                    request.iata(),
                    request.coordinates(),
                    request.airport_location(),
                    request.airport_type(),
                    request.runway_info(),
                    request.status(),
                    request.time_zone(),
                    request.operational_hours(),
                    request.contact_info(),
                    request.terminals(),
                    request.gates(),
                    request.services(),
                    request.images());

            Map<String, Object> data = new HashMap<>();
            data.put("iata", airport.obtainIata().iata());

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);

            Link self_link = linkTo(methodOn(AirportController.class)
                    .getAirportByIata(airport.obtainIata().iata())).withSelfRel();
            resource.add(self_link);
            Link return_link = linkTo(methodOn(AirportController.class)
                    .getAllAirports()).withRel("return");
            resource.add(return_link);

            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/{iata}")
    public ResponseEntity<?> getAirportByIata(@PathVariable String iata) {
        try {
            LocationIdentifier location_id = new LocationIdentifier(iata);
            Optional<Airport> airport = airport_repository.findByIata(location_id);
            if (airport.isPresent()) {
                return ResponseEntity.ok(airport.get());
            }
            return ResponseEntity.notFound().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllAirports() {
        List<Airport> airports = (List<Airport>) airport_repository.findAll();
        return ResponseEntity.ok(airports);

    }
}
package com.marslps.AISafeFMS.application.controllers;

import com.marslps.AISafeFMS.application.model.CertifyAircraftModelRequest;
import com.marslps.AISafeFMS.application.model.UpdateAirportRequest;
import com.marslps.AISafeFMS.application.model.UpdateAirportStatusRequest;
import com.marslps.AISafeFMS.application.use_cases.*;
import com.marslps.AISafeFMS.application.assemblers.AirportAssembler;
import com.marslps.AISafeFMS.application.dto.AirportStatisticsDTO;
import com.marslps.AISafeFMS.application.dto.AirportDTO;
import com.marslps.AISafeFMS.application.model.RegisterAirportRequest;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.vo.*;

import com.marslps.AISafeFMS.repository.AirportRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final RegisterAirportUseCase register_airport;
    private final CertifyAircraftUseCase certify_aircraft;
    private final ViewAirportDetailsUseCase view_airport_details;
    private final SearchAirportsUseCase search_airports;
    private final UpdateAirportStatusUseCase update_airport_status;
    private final UpdateAirportUseCase update_airport;
    private final ViewGroupedAirportsUseCase view_grouped_airports;
    private final ViewAirportRoutesUseCase view_airport_routes;
    private final GenerateStatisticsUseCase generate_statistics;
    private final AirportRepository airport_repository;
    private final AirportAssembler airport_assembler;

    public AirportController(RegisterAirportUseCase register_airport, CertifyAircraftUseCase certify_aircraft, ViewAirportDetailsUseCase view_airport_details, SearchAirportsUseCase search_airports, UpdateAirportStatusUseCase update_airport_status, UpdateAirportUseCase update_airport, ViewGroupedAirportsUseCase view_grouped_airports, ViewAirportRoutesUseCase view_airport_routes, GenerateStatisticsUseCase generate_statistics, AirportRepository airport_repository, AirportAssembler airport_assembler) {
        this.register_airport = register_airport;
        this.certify_aircraft = certify_aircraft;
        this.view_airport_details = view_airport_details;
        this.search_airports = search_airports;
        this.update_airport_status = update_airport_status;
        this.update_airport = update_airport;
        this.view_grouped_airports = view_grouped_airports;
        this.view_airport_routes = view_airport_routes;
        this.generate_statistics = generate_statistics;
        this.airport_repository = airport_repository;
        this.airport_assembler = airport_assembler;
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
                    .searchAirports(null, null, null)).withRel("return");
            resource.add(return_link);

            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{iata}/certifications")
    @PreAuthorize("hasAnyAuthority('BACKOFFICE_OP', 'ATCC')")
    public ResponseEntity<?> certifyAircraftModel(@PathVariable String iata, @Valid @RequestBody CertifyAircraftModelRequest request) {
        try {
            LocationIdentifier iata_code = new LocationIdentifier(iata);
            Airport airport = certify_aircraft.execute(iata_code, request.name());

            AirportDTO resource = airport_assembler.toModel(airport);

            Link self_link = linkTo(methodOn(AirportController.class)
                    .getAirportByIata(airport.obtainIata().iata())).withSelfRel();
            resource.add(self_link);
            Link return_link = linkTo(methodOn(AirportController.class)
                    .searchAirports(null, null, null)).withRel("return");
            resource.add(return_link);

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/{iata}")
    @PreAuthorize("hasAnyAuthority('BACKOFFICE_OP', 'ATCC')")
    public ResponseEntity<?> getAirportByIata(@PathVariable String iata) {
        try {
            LocationIdentifier iata_code = new LocationIdentifier(iata);
            Airport airport = view_airport_details.execute(iata_code);
            
            AirportDTO resource = airport_assembler.toModel(airport);
            
            Link self_link = linkTo(methodOn(AirportController.class)
                    .getAirportByIata(iata)).withSelfRel();
            resource.add(self_link);
            Link return_link = linkTo(methodOn(AirportController.class)
                    .searchAirports(null, null, null)).withRel("airports");
            resource.add(return_link);
            
            return ResponseEntity.ok(resource);

        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PatchMapping("/{iata}")
    @PreAuthorize("hasAuthority('BACKOFFICE_OP')")
    public ResponseEntity<?> updateAirportStatus(@PathVariable String iata, @Valid @RequestBody UpdateAirportStatusRequest request) {
        try {
            LocationIdentifier iata_code = new LocationIdentifier(iata);
            AirportStatus status;
            try {
                status = AirportStatus.valueOf(request.status().toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("The request's data is malformed");
            }

            Airport airport = update_airport_status.execute(iata_code, status);

            Map<String, Object> data = new HashMap<>();
            data.put("status", airport.getStatus());

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);
            Link self_link = linkTo(methodOn(AirportController.class).getAirportByIata(iata)).withSelfRel();
            resource.add(self_link);

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> searchAirports(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String name) {

        try {
            List<Airport> airports = search_airports.execute(city, country, name);

            List<AirportDTO> airport_resources = new ArrayList<>();
            for (Airport airport : airports) {
                AirportDTO airport_model = airport_assembler.toModel(airport);
                Link airport_link = linkTo(methodOn(AirportController.class)
                        .getAirportByIata(airport.obtainIata().iata())).withSelfRel();
                airport_model.add(airport_link);
                airport_resources.add(airport_model);
            }

            Link self_link = linkTo(methodOn(AirportController.class)
                    .searchAirports(city, country, name)).withSelfRel();
            CollectionModel<AirportDTO> resource = CollectionModel.of(airport_resources, self_link);

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{iata}")
    @PreAuthorize("hasAuthority('BACKOFFICE_OP')")
    public ResponseEntity<?> updateAirportDetails(@PathVariable String iata, @Valid @RequestBody UpdateAirportRequest request) {
        try {
            LocationIdentifier iata_code = new LocationIdentifier(iata);
            Airport updated_airport = update_airport.execute(iata_code, request);
            AirportDTO resource = airport_assembler.toModel(updated_airport);
            
            Link self_link = linkTo(methodOn(AirportController.class).getAirportByIata(iata)).withSelfRel();
            resource.add(self_link);
            
            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/grouped")
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> viewGroupedAirports(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String country) {

        try {
            List<Airport> airports = view_grouped_airports.execute(region, country);

            List<AirportDTO> airport_resources = new ArrayList<>();
            for (Airport airport : airports) {
                AirportDTO airport_model = airport_assembler.toModel(airport);
                Link airport_link = linkTo(methodOn(AirportController.class)
                        .getAirportByIata(airport.obtainIata().iata())).withSelfRel();
                airport_model.add(airport_link);
                airport_resources.add(airport_model);
            }

            Link self_link = linkTo(methodOn(AirportController.class)
                    .viewGroupedAirports(region, country)).withSelfRel();
            CollectionModel<AirportDTO> resource = CollectionModel.of(airport_resources, self_link);

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/{iata}/routes")
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> getAirportRoutes(@PathVariable String iata) {
        try {
            LocationIdentifier iata_code = new LocationIdentifier(iata);
            List<Route> routes = view_airport_routes.execute(iata_code);

            Link self_link = linkTo(methodOn(AirportController.class).getAirportRoutes(iata)).withSelfRel();
            CollectionModel<Route> resource = CollectionModel.of(routes, self_link);

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/statistics")
    @PreAuthorize("hasAuthority('BACKOFFICE_OP')")
    public ResponseEntity<?> getAirportStatistics() {
        try {
            List<AirportStatisticsDTO> stats = generate_statistics.execute();
            Link self_link = linkTo(methodOn(AirportController.class).getAirportStatistics()).withSelfRel();
            CollectionModel<AirportStatisticsDTO> resource = CollectionModel.of(stats, self_link);
            
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
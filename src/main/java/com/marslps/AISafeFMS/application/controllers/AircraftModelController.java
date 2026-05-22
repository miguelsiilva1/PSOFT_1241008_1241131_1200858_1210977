package com.marslps.AISafeFMS.application.controllers;

import com.marslps.AISafeFMS.application.model.RegisterAircraftModelRequest;
import com.marslps.AISafeFMS.application.use_cases.RegisterAircraftModelUseCase;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/aircraft-model")
public class AircraftModelController {
    private final RegisterAircraftModelUseCase register_aircraft_model;
    private final AircraftModelRepository aircraft_model_repo;

    public AircraftModelController(RegisterAircraftModelUseCase register_aircraft_model,
                                   AircraftModelRepository aircraft_model_repo) {
        this.register_aircraft_model = register_aircraft_model;
        this.aircraft_model_repo = aircraft_model_repo;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('BACKOFFICE_OP')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerAircraftModel(@Valid @RequestBody RegisterAircraftModelRequest request) {
        try {
            AircraftModel aircraft_model = register_aircraft_model.execute(request.name(),
                    request.seating_capacity(),
                    request.max_range(),
                    request.fuel_capacity(),
                    request.cruising_speed(),
                    request.manufacturer_name());

            Map<String, Object> data = new HashMap<>();
            data.put("name", aircraft_model.obtainName());

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);

            Link self_link = linkTo(methodOn(AircraftModelController.class)
                    .getAircraftModelByName(aircraft_model.obtainName())).withSelfRel();
            resource.add(self_link);
            Link return_link = linkTo(methodOn(AircraftModelController.class)
                    .getAllAircraftModels()).withRel("return");
            resource.add(return_link);

            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "The request's data is malformed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyAuthority('BACKOFFICE_OP', 'ATCC')")
    public ResponseEntity<?> getAircraftModelByName(@PathVariable String name) {
        try {
            List<AircraftModel> models = aircraft_model_repo.findByName(name);
            return ResponseEntity.ok(models);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('BACKOFFICE_OP', 'ATCC')")
    public ResponseEntity<?> getAllAircraftModels() {
        List<AircraftModel> models = (List<AircraftModel>) aircraft_model_repo.findAll();
        return ResponseEntity.ok(models);
    }
}

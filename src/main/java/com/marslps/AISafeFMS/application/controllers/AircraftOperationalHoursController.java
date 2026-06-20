package com.marslps.AISafeFMS.application.controllers;

import com.marslps.AISafeFMS.application.use_cases.AircraftOperationalHoursUseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.Route;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/aircraft-op-hours")
public class AircraftOperationalHoursController {
    private AircraftOperationalHoursUseCase aoh_use_case;

    public AircraftOperationalHoursController(AircraftOperationalHoursUseCase aoh_use_case) {
        this.aoh_use_case = aoh_use_case;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> getAll() {
        try {
            List<Aircraft> aircrafts = (List<Aircraft>) aoh_use_case.execute();

            List<EntityModel<Map<String, Object>>> resources = aircrafts.stream().map(a -> {
                Map<String, Object> data = new HashMap<>();
                data.put("registration_number", a.obtainRegistrationNumber());
                data.put("operational_hours", a.obtainFlightHours());

                return EntityModel.of(data)
                        .add(linkTo(methodOn(AircraftController.class).getAllAircrafts()).withRel("return"));
            }).toList();
            return ResponseEntity.ok(resources);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "The request's data is malformed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}

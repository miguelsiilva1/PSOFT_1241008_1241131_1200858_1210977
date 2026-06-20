package com.marslps.AISafeFMS.application.controllers;

import com.marslps.AISafeFMS.application.use_cases.AircraftRealtimeStatusUseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/aircraft-realtime-status")
public class AircraftRealtimeStatusController {
    private final AircraftRealtimeStatusUseCase ars_use_case;

    public AircraftRealtimeStatusController(AircraftRealtimeStatusUseCase ars_use_case) {
        this.ars_use_case = ars_use_case;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ATCC')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> checkAircraftStatus(@RequestParam("registration_number") String registration_number) {
        try {
            Aircraft aircraft = ars_use_case.execute(registration_number);

            Map<String, Object> data = new HashMap<>();
            data.put("registration_number", registration_number);
            data.put("status", aircraft.obtainAircraftStatus());

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);

            Link self_link = linkTo(methodOn(AircraftController.class)
                    .searchAircraftByRegistrationNumber(registration_number)).withSelfRel();
            resource.add(self_link);
            Link return_link = linkTo(methodOn(AircraftController.class)
                    .getAllAircrafts()).withRel("return");
            resource.add(return_link);
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "The request's data is malformed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}

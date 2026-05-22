package com.marslps.AISafeFMS.application.controllers;

import com.marslps.AISafeFMS.application.model.RegisterAircraftRequest;
import com.marslps.AISafeFMS.application.model.UpdateAircraftStatusRequest;
import com.marslps.AISafeFMS.application.use_cases.FindAircraftByModelStatusManufactoringYearUseCase;
import com.marslps.AISafeFMS.application.use_cases.FindAircraftByRegistrationNumberUseCase;
import com.marslps.AISafeFMS.application.use_cases.RegisterAircraftUseCase;
import com.marslps.AISafeFMS.application.use_cases.UpdateAircraftStatusUseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.repository.AircraftRepository;
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
@RequestMapping("/api/aircraft")
public class AircraftController {
    private final RegisterAircraftUseCase register_aircraft;
    private final FindAircraftByRegistrationNumberUseCase find_aircraft_by_registration_number;
    private final FindAircraftByModelStatusManufactoringYearUseCase find_aircraft_by_model_status_manufactoring_year;
    private final UpdateAircraftStatusUseCase update_aircraft_status;
    private final AircraftRepository aircraft_repo;

    public AircraftController(RegisterAircraftUseCase register_aircraft,
                              FindAircraftByRegistrationNumberUseCase find_aircraft_by_registration_number,
                              FindAircraftByModelStatusManufactoringYearUseCase findAircraftByModelStatusManufactoringYear,
                              UpdateAircraftStatusUseCase updateAircraftStatus,
                              AircraftRepository aircraft_repo) {
        this.register_aircraft = register_aircraft;
        this.find_aircraft_by_registration_number = find_aircraft_by_registration_number;
        find_aircraft_by_model_status_manufactoring_year = findAircraftByModelStatusManufactoringYear;
        update_aircraft_status = updateAircraftStatus;
        this.aircraft_repo = aircraft_repo;
    }

    @PostMapping()
    @PreAuthorize("hasRole('BACKOFFICE_OP')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerAircraft(@Valid @RequestBody RegisterAircraftRequest request) {
        try {
            Aircraft aircraft = register_aircraft.execute(request.registration_number(),
                    request.model_name(),
                    request.manufacturing_date(),
                    request.last_maintenance(),
                    request.max_flight_hours_until_maintenance(),
                    request.max_days_until_maintenance(),
                    request.economy_seats(),
                    request.premium_economy_seats(),
                    request.business_seats(),
                    request.first_seats(),
                    request.status());

            Map<String, Object> data = new HashMap<>();
            data.put("registration_number", aircraft.obtainRegistrationNumber());

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);

            Link self_link = linkTo(methodOn(AircraftController.class)
                    .searchAircraftByRegistrationNumber(aircraft.obtainRegistrationNumber())).withSelfRel();
            resource.add(self_link);
            Link return_link = linkTo(methodOn(AircraftController.class)
                    .getAllAircrafts()).withRel("return");
            resource.add(return_link);

            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "The request's data is malformed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/{registration_number}")
    @PreAuthorize("hasRole('BACKOFFICE_OP'||'ATCC')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> searchAircraftByRegistrationNumber(@Valid @RequestBody String registration_number) {
        try {
            Aircraft aircraft = find_aircraft_by_registration_number.execute(registration_number);

            Map<String, Object> data = new HashMap<>();
            data.put("registration_number", aircraft.obtainRegistrationNumber());

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);

            Link return_link = linkTo(methodOn(AircraftController.class)
                    .getAllAircrafts()).withRel("return");
            resource.add(return_link);

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('ATCC')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> searchAircraftByModel(@Valid @RequestBody String model_name) {
        try {
            List<Aircraft> aircrafts = find_aircraft_by_model_status_manufactoring_year.execute(model_name);

            Map<String, Object> data = new HashMap<>();
            for(Aircraft a : aircrafts) {data.put("registration_number", a.obtainRegistrationNumber());}

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);

            Link return_link = linkTo(methodOn(AircraftController.class)
                    .getAllAircrafts()).withRel("return");
            resource.add(return_link);

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping()
    @PreAuthorize("hasRole('ATCC')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> searchAircraftByStatus(@Valid @RequestBody String status) {
        try {
            List<Aircraft> aircrafts = find_aircraft_by_model_status_manufactoring_year.execute(AircraftStatus.valueOf(status));

            Map<String, Object> data = new HashMap<>();
            for(Aircraft a : aircrafts) {data.put("registration_number", a.obtainRegistrationNumber());}

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);

            Link return_link = linkTo(methodOn(AircraftController.class)
                    .getAllAircrafts()).withRel("return");
            resource.add(return_link);

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping()
    @PreAuthorize("hasRole('ATCC')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> searchAircraftByManufacturingYear(@Valid @RequestBody int year) {
        try {
            List<Aircraft> aircrafts = find_aircraft_by_model_status_manufactoring_year.execute(year);

            Map<String, Object> data = new HashMap<>();
            for(Aircraft a : aircrafts) {data.put("registration_number", a.obtainRegistrationNumber());}

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);

            Link return_link = linkTo(methodOn(AircraftController.class)
                    .getAllAircrafts()).withRel("return");
            resource.add(return_link);

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping()
    @PreAuthorize("hasRole('ATCC')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateAircraftStatus(@Valid @RequestBody UpdateAircraftStatusRequest request) {
        try {
            Aircraft aircraft = update_aircraft_status.execute(request.registration_number(),
                    AircraftStatus.valueOf(request.status()));

            Map<String, Object> data = new HashMap<>();
            data.put("registration_number", aircraft.obtainRegistrationNumber());
            data.put("new_status", aircraft.obtainAircraftStatus());

            EntityModel<Map<String, Object>> resource = EntityModel.of(data);

            Link self_link = linkTo(methodOn(AircraftController.class)
                    .searchAircraftByRegistrationNumber(aircraft.obtainRegistrationNumber())).withSelfRel();
            resource.add(self_link);
            Link return_link = linkTo(methodOn(AircraftController.class)
                    .getAllAircrafts()).withRel("return");
            resource.add(return_link);

            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "The request's data is malformed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllAircrafts() {
        List<Aircraft> aircrafts = (List<Aircraft>) aircraft_repo.findAll();
        return ResponseEntity.ok(aircrafts);
    }
}

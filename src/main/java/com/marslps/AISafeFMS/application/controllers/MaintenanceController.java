package com.marslps.AISafeFMS.application.controllers;

import com.marslps.AISafeFMS.application.model.*;
import com.marslps.AISafeFMS.application.use_cases.*;
import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.model.entities.MaintenanceTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/maintenance")
@Tag(name = "Maintenance Management", description = "Endpoints for managing maintenance templates and records (WP4A)")
public class MaintenanceController {

    private final CreateMaintenanceTemplateUseCase createMaintenanceTemplateUseCase;
    private final CreateMaintenanceRecordUseCase createMaintenanceRecordUseCase;
    private final ViewMaintenanceRecordsUseCase viewMaintenanceRecordsUseCase;
    private final ViewMaintenanceHoursUseCase viewMaintenanceHoursUseCase;
    private final CompleteMaintenanceRecordUseCase completeMaintenanceRecordUseCase;
    private final CategorizeMaintenanceRecordUseCase categorizeUseCase;
    private final SearchMaintenanceRecordsUseCase searchMaintenanceRecordsUseCase;
    private final GenerateMaintenanceCostReportUseCase generateMaintenanceCostReportUseCase;
    private final ViewAverageTurnaroundTimeUseCase viewAverageTurnaroundTimeUseCase;
    private final GetMaintenanceAlertsUseCase getMaintenanceAlertsUseCase;

    // INJETADO PARA A US219 CORRETA: Atividades em curso
    private final ViewOngoingMaintenancesUseCase viewOngoingMaintenancesUseCase;

    public MaintenanceController(CreateMaintenanceTemplateUseCase createMaintenanceTemplateUseCase,
                                 CreateMaintenanceRecordUseCase createMaintenanceRecordUseCase,
                                 ViewMaintenanceRecordsUseCase viewMaintenanceRecordsUseCase,
                                 ViewMaintenanceHoursUseCase viewMaintenanceHoursUseCase,
                                 CompleteMaintenanceRecordUseCase completeMaintenanceRecordUseCase,
                                 CategorizeMaintenanceRecordUseCase categorizeUseCase,
                                 SearchMaintenanceRecordsUseCase searchMaintenanceRecordsUseCase,
                                 GenerateMaintenanceCostReportUseCase generateMaintenanceCostReportUseCase,
                                 ViewAverageTurnaroundTimeUseCase viewAverageTurnaroundTimeUseCase,
                                 GetMaintenanceAlertsUseCase getMaintenanceAlertsUseCase,
                                 ViewOngoingMaintenancesUseCase viewOngoingMaintenancesUseCase) {
        this.createMaintenanceTemplateUseCase = createMaintenanceTemplateUseCase;
        this.createMaintenanceRecordUseCase = createMaintenanceRecordUseCase;
        this.viewMaintenanceRecordsUseCase = viewMaintenanceRecordsUseCase;
        this.viewMaintenanceHoursUseCase = viewMaintenanceHoursUseCase;
        this.completeMaintenanceRecordUseCase = completeMaintenanceRecordUseCase;
        this.categorizeUseCase = categorizeUseCase;
        this.searchMaintenanceRecordsUseCase = searchMaintenanceRecordsUseCase;
        this.generateMaintenanceCostReportUseCase = generateMaintenanceCostReportUseCase;
        this.viewAverageTurnaroundTimeUseCase = viewAverageTurnaroundTimeUseCase;
        this.getMaintenanceAlertsUseCase = getMaintenanceAlertsUseCase;
        this.viewOngoingMaintenancesUseCase = viewOngoingMaintenancesUseCase;
    }

    @PostMapping("/templates")
    @PreAuthorize("hasAuthority('MAINTENANCE_TECH')")
    public ResponseEntity<?> createMaintenanceTemplate(@Valid @RequestBody CreateMaintenanceTemplateRequest request) {
        try {
            MaintenanceTemplate template = createMaintenanceTemplateUseCase.execute(request);
            MaintenanceTemplateResponse response = MaintenanceTemplateResponse.from(template);
            EntityModel<MaintenanceTemplateResponse> resource = EntityModel.of(response);
            resource.add(linkTo(methodOn(MaintenanceController.class).createMaintenanceTemplate(request)).withSelfRel());
            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/records")
    @PreAuthorize("hasAuthority('MAINTENANCE_TECH')")
    public ResponseEntity<?> createMaintenanceRecord(@Valid @RequestBody CreateMaintenanceRecordRequest request) {
        try {
            MaintenanceRecord record = createMaintenanceRecordUseCase.execute(request);
            MaintenanceRecordResponse response = MaintenanceRecordResponse.from(record);
            EntityModel<MaintenanceRecordResponse> resource = EntityModel.of(response);
            resource.add(linkTo(methodOn(MaintenanceController.class).viewMaintenanceRecordsForAircraft(record.getAircraft().obtainRegistrationNumber())).withRel("aircraft_records"));

            // noinspection ConstantConditions
            resource.add(linkTo(methodOn(MaintenanceController.class).completeMaintenanceRecord(record.obtainRecordId(), null)).withRel("complete"));
            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/aircraft/{registrationNumber}/records")
    @PreAuthorize("hasAnyAuthority('MAINTENANCE_TECH', 'ATCC')")
    public ResponseEntity<?> viewMaintenanceRecordsForAircraft(@PathVariable String registrationNumber) {
        try {
            List<MaintenanceRecord> records = viewMaintenanceRecordsUseCase.execute(registrationNumber);
            List<EntityModel<MaintenanceRecordResponse>> resources = records.stream()
                    .map(record -> {
                        EntityModel<MaintenanceRecordResponse> resource = EntityModel.of(MaintenanceRecordResponse.from(record));
                        resource.add(linkTo(methodOn(MaintenanceController.class).viewMaintenanceRecordsForAircraft(registrationNumber)).withRel("aircraft_records"));
                        return resource;
                    }).toList();
            return ResponseEntity.ok(CollectionModel.of(resources, linkTo(methodOn(MaintenanceController.class).viewMaintenanceRecordsForAircraft(registrationNumber)).withSelfRel()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/aircraft/{registrationNumber}/hours")
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> viewTotalMaintenanceHours(@PathVariable String registrationNumber) {
        try {
            double totalHours = viewMaintenanceHoursUseCase.execute(registrationNumber);
            MaintenanceHoursResponse response = new MaintenanceHoursResponse(registrationNumber, totalHours);
            EntityModel<MaintenanceHoursResponse> resource = EntityModel.of(response);
            resource.add(linkTo(methodOn(MaintenanceController.class).viewTotalMaintenanceHours(registrationNumber)).withSelfRel());
            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PatchMapping("/records/{recordId}/complete")
    @PreAuthorize("hasAuthority('MAINTENANCE_TECH')")
    public ResponseEntity<?> completeMaintenanceRecord(@PathVariable int recordId, @Valid @RequestBody CompleteMaintenanceRecordRequest request) {
        try {
            MaintenanceRecord record = completeMaintenanceRecordUseCase.execute(recordId, request.completion_notes());
            return ResponseEntity.ok(EntityModel.of(MaintenanceRecordResponse.from(record)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PatchMapping("/records/{recordId}/category")
    @PreAuthorize("hasAuthority('MAINTENANCE_TECH')")
    public ResponseEntity<?> categorizeRecord(@PathVariable int recordId, @RequestBody CategorizeMaintenanceRecordRequest request) {
        try {
            MaintenanceRecord updatedRecord = categorizeUseCase.execute(recordId, request.component());
            return ResponseEntity.ok(MaintenanceRecordResponse.from(updatedRecord));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/records")
    @PreAuthorize("hasAuthority('MAINTENANCE_TECH')")
    public ResponseEntity<?> searchMaintenanceRecords(
            @RequestParam(required = false) String aircraft,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false) String component) {
        try {
            List<MaintenanceRecord> records = searchMaintenanceRecordsUseCase.execute(aircraft, startDate, endDate, component);
            List<EntityModel<MaintenanceRecordResponse>> resources = records.stream()
                    .map(record -> EntityModel.of(MaintenanceRecordResponse.from(record))).toList();
            return ResponseEntity.ok(CollectionModel.of(resources));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/reports/costs")
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> getMaintenanceCostReport(@RequestParam(required = false, defaultValue = "aircraft") String groupBy) {
        try {
            List<MaintenanceCostReportResponse> report = generateMaintenanceCostReportUseCase.execute(groupBy);
            return ResponseEntity.ok(report);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/reports/turnaround")
    @PreAuthorize("hasAuthority('MAINTENANCE_SUPERVISOR')")
    public ResponseEntity<?> getAverageTurnaroundTime() {
        try {
            List<MaintenanceTurnaroundResponse> report = viewAverageTurnaroundTimeUseCase.execute();
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/alerts")
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> getMaintenanceAlerts() {
        try {
            List<AircraftMaintenanceAlertResponse> alerts = getMaintenanceAlertsUseCase.execute();
            return ResponseEntity.ok(alerts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    // =========================================================================
    // ENDPOINT ADICIONADO E CORRIGIDO PARA A US219 (Listar Manutenções Ativas)
    // =========================================================================
    @Operation(summary = "View all ongoing maintenance activities", description = "Allows a Maintenance Supervisor to view all active maintenance actions across the fleet.")
    @GetMapping("/ongoing")
    @PreAuthorize("hasAuthority('MAINTENANCE_SUPERVISOR')")
    public ResponseEntity<?> getOngoingMaintenances() {
        try {
            List<MaintenanceRecord> records = viewOngoingMaintenancesUseCase.execute();
            List<EntityModel<MaintenanceRecordResponse>> resources = records.stream()
                    .map(record -> EntityModel.of(MaintenanceRecordResponse.from(record)))
                    .toList();

            Link selfLink = linkTo(methodOn(MaintenanceController.class).getOngoingMaintenances()).withSelfRel();
            return ResponseEntity.ok(CollectionModel.of(resources, selfLink));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }
}
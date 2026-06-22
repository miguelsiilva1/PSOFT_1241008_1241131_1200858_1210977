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

    // INJETADO PARA A US220: Use Case para geração de relatórios de custos
    private final GenerateMaintenanceCostReportUseCase generateMaintenanceCostReportUseCase;

    public MaintenanceController(CreateMaintenanceTemplateUseCase createMaintenanceTemplateUseCase,
                                 CreateMaintenanceRecordUseCase createMaintenanceRecordUseCase,
                                 ViewMaintenanceRecordsUseCase viewMaintenanceRecordsUseCase,
                                 ViewMaintenanceHoursUseCase viewMaintenanceHoursUseCase,
                                 CompleteMaintenanceRecordUseCase completeMaintenanceRecordUseCase,
                                 CategorizeMaintenanceRecordUseCase categorizeUseCase,
                                 SearchMaintenanceRecordsUseCase searchMaintenanceRecordsUseCase,
                                 GenerateMaintenanceCostReportUseCase generateMaintenanceCostReportUseCase) {
        this.createMaintenanceTemplateUseCase = createMaintenanceTemplateUseCase;
        this.createMaintenanceRecordUseCase = createMaintenanceRecordUseCase;
        this.viewMaintenanceRecordsUseCase = viewMaintenanceRecordsUseCase;
        this.viewMaintenanceHoursUseCase = viewMaintenanceHoursUseCase;
        this.completeMaintenanceRecordUseCase = completeMaintenanceRecordUseCase;
        this.categorizeUseCase = categorizeUseCase;
        this.searchMaintenanceRecordsUseCase = searchMaintenanceRecordsUseCase;
        this.generateMaintenanceCostReportUseCase = generateMaintenanceCostReportUseCase;
    }

    @Operation(summary = "Create a new maintenance template", description = "Allows a Maintenance Technician to create a reusable template with a checklist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Template created successfully", content = @Content(schema = @Schema(implementation = MaintenanceTemplateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
    @PostMapping("/templates")
    @PreAuthorize("hasAuthority('MAINTENANCE_TECH')")
    public ResponseEntity<?> createMaintenanceTemplate(@Valid @RequestBody CreateMaintenanceTemplateRequest request) {
        try {
            MaintenanceTemplate template = createMaintenanceTemplateUseCase.execute(request);
            MaintenanceTemplateResponse response = MaintenanceTemplateResponse.from(template);

            EntityModel<MaintenanceTemplateResponse> resource = EntityModel.of(response);
            Link selfLink = linkTo(methodOn(MaintenanceController.class)
                    .createMaintenanceTemplate(request)).withSelfRel();
            resource.add(selfLink);

            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @Operation(summary = "Create a new maintenance record", description = "Creates a maintenance record for a specific aircraft and updates its status to 'Under Maintenance'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Record created successfully", content = @Content(schema = @Schema(implementation = MaintenanceRecordResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or aircraft/template not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
    @PostMapping("/records")
    @PreAuthorize("hasAuthority('MAINTENANCE_TECH')")
    public ResponseEntity<?> createMaintenanceRecord(@Valid @RequestBody CreateMaintenanceRecordRequest request) {
        try {
            MaintenanceRecord record = createMaintenanceRecordUseCase.execute(request);
            MaintenanceRecordResponse response = MaintenanceRecordResponse.from(record);

            EntityModel<MaintenanceRecordResponse> resource = EntityModel.of(response);
            Link selfLink = linkTo(methodOn(MaintenanceController.class)
                    .viewMaintenanceRecordsForAircraft(record.getAircraft().obtainRegistrationNumber())).withRel("aircraft_records");
            resource.add(selfLink);
            Link completeLink = linkTo(methodOn(MaintenanceController.class)
                    .completeMaintenanceRecord(record.obtainRecordId(), null)).withRel("complete");
            resource.add(completeLink);

            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @Operation(summary = "View maintenance records for an aircraft", description = "Retrieves the full maintenance history for a given aircraft registration number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Records retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Aircraft not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
    @GetMapping("/aircraft/{registrationNumber}/records")
    @PreAuthorize("hasAnyAuthority('MAINTENANCE_TECH', 'ATCC')")
    public ResponseEntity<?> viewMaintenanceRecordsForAircraft(
            @Parameter(description = "Aircraft Registration Number", required = true) @PathVariable String registrationNumber) {
        try {
            List<MaintenanceRecord> records = viewMaintenanceRecordsUseCase.execute(registrationNumber);
            List<EntityModel<MaintenanceRecordResponse>> resources = records.stream()
                    .map(record -> {
                        EntityModel<MaintenanceRecordResponse> resource = EntityModel.of(MaintenanceRecordResponse.from(record));
                        resource.add(linkTo(methodOn(MaintenanceController.class)
                                .viewMaintenanceRecordsForAircraft(registrationNumber)).withRel("aircraft_records"));
                        return resource;
                    })
                    .toList();

            Link selfLink = linkTo(methodOn(MaintenanceController.class)
                    .viewMaintenanceRecordsForAircraft(registrationNumber)).withSelfRel();

            return ResponseEntity.ok(CollectionModel.of(resources, selfLink));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @Operation(summary = "View total maintenance hours", description = "Calculates the total expected maintenance duration for a specific aircraft.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hours calculated successfully", content = @Content(schema = @Schema(implementation = MaintenanceHoursResponse.class))),
            @ApiResponse(responseCode = "400", description = "Aircraft not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
    @GetMapping("/aircraft/{registrationNumber}/hours")
    @PreAuthorize("hasAuthority('ATCC')")
    public ResponseEntity<?> viewTotalMaintenanceHours(
            @Parameter(description = "Aircraft Registration Number", required = true) @PathVariable String registrationNumber) {
        try {
            double totalHours = viewMaintenanceHoursUseCase.execute(registrationNumber);
            MaintenanceHoursResponse response = new MaintenanceHoursResponse(registrationNumber, totalHours);

            EntityModel<MaintenanceHoursResponse> resource = EntityModel.of(response);
            resource.add(linkTo(methodOn(MaintenanceController.class)
                    .viewTotalMaintenanceHours(registrationNumber)).withSelfRel());
            resource.add(linkTo(methodOn(MaintenanceController.class)
                    .viewMaintenanceRecordsForAircraft(registrationNumber)).withRel("records"));

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @Operation(summary = "Complete a maintenance record", description = "Marks an ongoing maintenance record as completed, adds notes, and sets the aircraft status back to Available.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record completed successfully", content = @Content(schema = @Schema(implementation = MaintenanceRecordResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or record already completed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
    @PatchMapping("/records/{recordId}/complete")
    @PreAuthorize("hasAuthority('MAINTENANCE_TECH')")
    public ResponseEntity<?> completeMaintenanceRecord(
            @Parameter(description = "Maintenance Record ID", required = true) @PathVariable int recordId,
            @Valid @RequestBody CompleteMaintenanceRecordRequest request) {
        try {
            MaintenanceRecord record = completeMaintenanceRecordUseCase.execute(recordId, request.completion_notes());
            MaintenanceRecordResponse response = MaintenanceRecordResponse.from(record);

            EntityModel<MaintenanceRecordResponse> resource = EntityModel.of(response);
            resource.add(linkTo(methodOn(MaintenanceController.class)
                    .viewMaintenanceRecordsForAircraft(record.getAircraft().obtainRegistrationNumber())).withRel("aircraft_records"));

            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PatchMapping("/records/{recordId}/category")
    @PreAuthorize("hasAuthority('MAINTENANCE_TECH')")
    public ResponseEntity<?> categorizeRecord(
            @PathVariable int recordId,
            @RequestBody CategorizeMaintenanceRecordRequest request) {
        try {
            MaintenanceRecord updatedRecord = categorizeUseCase.execute(recordId, request.component());
            MaintenanceRecordResponse response = MaintenanceRecordResponse.from(updatedRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @Operation(summary = "Search maintenance records with filters", description = "Allows a Maintenance Technician to search records by aircraft registration, date range, or component affected.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Records filtered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
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
                    .map(record -> {
                        EntityModel<MaintenanceRecordResponse> resource = EntityModel.of(MaintenanceRecordResponse.from(record));
                        resource.add(linkTo(methodOn(MaintenanceController.class)
                                .viewMaintenanceRecordsForAircraft(record.getAircraft().obtainRegistrationNumber())).withRel("aircraft_records"));
                        return resource;
                    })
                    .toList();

            Link selfLink = linkTo(methodOn(MaintenanceController.class)
                    .searchMaintenanceRecords(aircraft, startDate, endDate, component)).withSelfRel();

            return ResponseEntity.ok(CollectionModel.of(resources, selfLink));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }


    @Operation(summary = "Generate maintenance cost reports", description = "Allows an ATCC to view total maintenance costs grouped by aircraft or aircraft model.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report generated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid groupBy parameter supplied", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
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
}
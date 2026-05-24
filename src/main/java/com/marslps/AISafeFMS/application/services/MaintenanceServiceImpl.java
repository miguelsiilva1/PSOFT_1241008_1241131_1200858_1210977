package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.application.model.*;
import com.marslps.AISafeFMS.model.entities.*;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
import com.marslps.AISafeFMS.repository.MaintenanceTemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaintenanceServiceImpl {

    // NOTA: Usa o Alt+Enter para importar os teus Repositórios se ficarem vermelhos!
    private final AircraftRepository aircraftRepository;
    private final MaintenanceTemplateRepository maintenanceTemplateRepository;
    private final MaintenanceRecordRepository maintenanceRecordRepository;

    public MaintenanceServiceImpl(AircraftRepository aircraftRepository,
                                  MaintenanceTemplateRepository maintenanceTemplateRepository,
                                  MaintenanceRecordRepository maintenanceRecordRepository) {
        this.aircraftRepository = aircraftRepository;
        this.maintenanceTemplateRepository = maintenanceTemplateRepository;
        this.maintenanceRecordRepository = maintenanceRecordRepository;
    }

    @Transactional
    public MaintenanceTemplate createMaintenanceTemplate(CreateMaintenanceTemplateRequest request) {
        // Usa o construtor do teu MaintenanceTemplate aqui
        MaintenanceTemplate template = new MaintenanceTemplate(request.type(), request.name(), request.applicable_models(), request.cost());
        return maintenanceTemplateRepository.save(template);
    }

    @Transactional
    public MaintenanceRecord createMaintenanceRecord(CreateMaintenanceRecordRequest request) {
        Aircraft aircraft = aircraftRepository.findByRegistrationNumber(request.aircraft_registration());
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft not found.");
        }

        MaintenanceTemplate template = maintenanceTemplateRepository.findById((long) request.template_id())
                .orElseThrow(() -> new IllegalArgumentException("Maintenance template not found."));

        if (!template.getApplicable_models().contains(aircraft.obtainModelName())) {
            throw new IllegalArgumentException("Maintenance template is not applicable to this aircraft model.");
        }

        MaintenanceRecord record = new MaintenanceRecord(
                template, aircraft, request.description(), request.start_date(), request.expected_duration(), false
        );

        MaintenanceRecord savedRecord = maintenanceRecordRepository.save(record);

        // CORREÇÃO QUE FIZEMOS ANTES: Alterar estado e salvar avião
        aircraft.changeStatus(AircraftStatus.UNDER_MAINTENANCE);
        aircraftRepository.save(aircraft);

        return savedRecord;
    }

    public List<MaintenanceRecord> getMaintenanceRecordsForAircraft(String registrationNumber) {
        Aircraft aircraft = aircraftRepository.findByRegistrationNumber(registrationNumber);
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft not found.");
        }
        return maintenanceRecordRepository.findByAircraftRegistrationNumber(registrationNumber);
    }

    public double getTotalMaintenanceHoursForAircraft(String registrationNumber) {
        Aircraft aircraft = aircraftRepository.findByRegistrationNumber(registrationNumber);
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft not found.");
        }
        Double hours = maintenanceRecordRepository.totalMaintenanceHoursByAircraftRegistrationNumber(registrationNumber);
        return hours != null ? hours : 0.0;
    }

    @Transactional
    public MaintenanceRecord completeMaintenanceRecord(int maintenanceRecordId, String completionNotes) {
        MaintenanceRecord record = maintenanceRecordRepository.findById(maintenanceRecordId)
                .orElseThrow(() -> new IllegalArgumentException("Maintenance record not found."));

        record.complete(completionNotes);
        MaintenanceRecord savedRecord = maintenanceRecordRepository.save(record);

        // CORREÇÃO QUE FIZEMOS ANTES: Alterar estado e salvar avião
        Aircraft aircraft = record.getAircraft();
        aircraft.changeStatus(AircraftStatus.AVAILABLE);
        aircraftRepository.save(aircraft);

        return savedRecord;
    }
}
package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.services.MaintenanceServiceImpl;
import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewMaintenanceRecordsUseCase {
    private final MaintenanceServiceImpl service;
    public ViewMaintenanceRecordsUseCase(MaintenanceServiceImpl service) { this.service = service; }

    public List<MaintenanceRecord> execute(String registrationNumber) {
        return service.getMaintenanceRecordsForAircraft(registrationNumber);
    }
}
package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.model.CreateMaintenanceRecordRequest;
import com.marslps.AISafeFMS.application.services.MaintenanceServiceImpl;
import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import org.springframework.stereotype.Component;

@Component
public class CreateMaintenanceRecordUseCase {
    private final MaintenanceServiceImpl service;
    public CreateMaintenanceRecordUseCase(MaintenanceServiceImpl service) { this.service = service; }

    public MaintenanceRecord execute(CreateMaintenanceRecordRequest request) {
        return service.createMaintenanceRecord(request);
    }
}
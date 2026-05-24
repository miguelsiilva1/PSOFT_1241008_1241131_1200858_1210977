package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.services.MaintenanceServiceImpl;
import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import org.springframework.stereotype.Component;


@Component
public class CompleteMaintenanceRecordUseCase {
    private final MaintenanceServiceImpl service;
    public CompleteMaintenanceRecordUseCase(MaintenanceServiceImpl service) { this.service = service; }

    public MaintenanceRecord execute(int recordId, String completionNotes) {
        return service.completeMaintenanceRecord(recordId, completionNotes);
    }
}
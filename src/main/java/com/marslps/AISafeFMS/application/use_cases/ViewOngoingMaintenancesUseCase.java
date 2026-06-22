package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewOngoingMaintenancesUseCase {

    private final MaintenanceRecordRepository repository;

    public ViewOngoingMaintenancesUseCase(MaintenanceRecordRepository repository) {
        this.repository = repository;
    }

    public List<MaintenanceRecord> execute() {
        return repository.findByCompletedFalse();
    }
}
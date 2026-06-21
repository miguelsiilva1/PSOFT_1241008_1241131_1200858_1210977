package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;

@UseCase
public class CategorizeMaintenanceRecordUseCase {

    private final MaintenanceRecordRepository repository;

    public CategorizeMaintenanceRecordUseCase(MaintenanceRecordRepository repository) {
        this.repository = repository;
    }

    public MaintenanceRecord execute(int recordId, String componentName) {

        MaintenanceRecord record = repository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));


        MaintenanceComponent component = MaintenanceComponent.valueOf(componentName.toUpperCase());

        record.categorize(component);


        return repository.save(record);
    }
}
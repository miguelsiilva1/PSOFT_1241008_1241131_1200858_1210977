package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchMaintenanceRecordsUseCase {

    private final MaintenanceRecordRepository repository;

    public SearchMaintenanceRecordsUseCase(MaintenanceRecordRepository repository) {
        this.repository = repository;
    }

    public List<MaintenanceRecord> execute(String aircraft, Date startDate, Date endDate, String componentStr) {
        if (startDate != null && endDate != null && startDate.after(endDate)) {
            throw new IllegalArgumentException("The start date cannot be after the end date.");
        }

        MaintenanceComponent component = null;
        if (componentStr != null && !componentStr.isBlank()) {
            try {
                component = MaintenanceComponent.valueOf(componentStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("The requested maintenance component category is invalid.");
            }
        }

        return repository.searchRecords(aircraft, startDate, endDate, component);
    }
}
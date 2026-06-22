package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.model.MaintenanceCostReportResponse;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerateMaintenanceCostReportUseCase {

    private final MaintenanceRecordRepository repository;

    public GenerateMaintenanceCostReportUseCase(MaintenanceRecordRepository repository) {
        this.repository = repository;
    }

    public List<MaintenanceCostReportResponse> execute(String groupBy) {
        List<Object[]> results;

        if ("model".equalsIgnoreCase(groupBy)) {
            results = repository.getCostReportPerModel();
        } else if ("aircraft".equalsIgnoreCase(groupBy) || groupBy == null) {
            results = repository.getCostReportPerAircraft();
        } else {
            throw new IllegalArgumentException("Invalid groupBy parameter. Use 'aircraft' or 'model'.");
        }

        return results.stream()
                .map(row -> new MaintenanceCostReportResponse(
                        (String) row[0],
                        (Double) row[1]
                ))
                .collect(Collectors.toList());
    }
}
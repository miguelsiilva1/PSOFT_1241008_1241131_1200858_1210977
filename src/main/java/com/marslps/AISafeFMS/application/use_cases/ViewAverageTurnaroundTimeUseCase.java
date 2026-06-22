package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.model.MaintenanceTurnaroundResponse;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ViewAverageTurnaroundTimeUseCase {

    private final MaintenanceRecordRepository repository;

    public ViewAverageTurnaroundTimeUseCase(MaintenanceRecordRepository repository) {
        this.repository = repository;
    }

    public List<MaintenanceTurnaroundResponse> execute() {

        List<Object[]> results = repository.findCompletedTurnaroundData();

        Map<String, List<Object[]>> groupedByModel = results.stream()
                .filter(row -> row[1] != null && row[2] != null)
                .collect(Collectors.groupingBy(row -> (String) row[0]));


        return groupedByModel.entrySet().stream().map(entry -> {
            String modelName = entry.getKey();
            List<Object[]> rows = entry.getValue();

            double avgHours = rows.stream().mapToLong(row -> {
                Date startDate = (Date) row[1];
                Date endDate = (Date) row[2];
                long diffInMs = endDate.getTime() - startDate.getTime();
                return diffInMs / (1000 * 60 * 60); // Converte milissegundos para horas
            }).average().orElse(0.0);


            double roundedAvg = Math.round(avgHours * 100.0) / 100.0;

            return new MaintenanceTurnaroundResponse(modelName, roundedAvg);
        }).collect(Collectors.toList());
    }
}
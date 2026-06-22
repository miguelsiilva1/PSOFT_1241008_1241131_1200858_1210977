package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.model.AircraftMaintenanceAlertResponse;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GetMaintenanceAlertsUseCase {

    private final AircraftRepository aircraftRepository;

    public GetMaintenanceAlertsUseCase(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public List<AircraftMaintenanceAlertResponse> execute() {
        List<Aircraft> allAircraft = aircraftRepository.getAllAircraft();
        List<AircraftMaintenanceAlertResponse> alerts = new ArrayList<>();
        Date now = new Date();

        for (Aircraft aircraft : allAircraft) {
            boolean needsAlert = false;
            StringBuilder reason = new StringBuilder();

            // 1. Verificação das Horas de Voo contra o limite máximo do avião
            double currentHours = aircraft.obtainFlightHours();
            int maxHours = aircraft.obtainMaxFlightHoursUntilMaintenance();
            if (currentHours >= maxHours) {
                needsAlert = true;
                reason.append("Flight hours limit exceeded (").append(currentHours).append("/").append(maxHours).append("h). ");
            }

            // 2. Verificação dos Dias de Calendário (Last Maintenance ou Manufacturing Date)
            Date referenceDate = aircraft.obtainLastMaintenance() != null ?
                    aircraft.obtainLastMaintenance() :
                    aircraft.obtainManufacturingDate();

            long daysSince = 0;
            int maxDays = aircraft.obtainMaxDaysUntilMaintenance();
            if (referenceDate != null) {
                long diffInMs = now.getTime() - referenceDate.getTime();
                daysSince = diffInMs / (1000 * 60 * 60 * 24);

                if (daysSince >= maxDays) {
                    needsAlert = true;
                    reason.append("Calendar days limit exceeded (").append(daysSince).append("/").append(maxDays).append(" days).");
                }
            }

            if (needsAlert) {
                alerts.add(new AircraftMaintenanceAlertResponse(
                        aircraft.obtainRegistrationNumber(),
                        reason.toString().trim(),
                        currentHours,
                        daysSince
                ));
            }
        }

        return alerts;
    }
}
package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.services.MaintenanceServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class ViewMaintenanceHoursUseCase {
    private final MaintenanceServiceImpl service;
    public ViewMaintenanceHoursUseCase(MaintenanceServiceImpl service) { this.service = service; }

    public double execute(String registrationNumber) {
        return service.getTotalMaintenanceHoursForAircraft(registrationNumber);
    }
}